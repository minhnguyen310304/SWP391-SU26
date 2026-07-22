package com.autowashpro.controller;

import com.autowashpro.dto.request.CheckoutRequest;
import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.dto.response.AvailableSlotResponse;
import com.autowashpro.dto.response.BookingContextResponse;
import com.autowashpro.dto.response.BookingResponse;
import com.autowashpro.dto.response.TransactionSummaryResponse;
import com.autowashpro.service.BookService;
import com.autowashpro.service.CheckoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Map;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bookings")
@RequiredArgsConstructor
public class BookingFlowController {
    private final CheckoutService checkoutService;
    private final BookService bookService;

    @GetMapping("/context")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<BookingContextResponse> getBookingContext(Authentication authentication) {
        return ResponseEntity.ok(checkoutService.getBookingContext(authentication.getName()));
    }

    @GetMapping("/history")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<List<BookingResponse>> getBookingHistory(Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllByCustomer(authentication.getName()));
    }

    @GetMapping("/available-slots")
    public ResponseEntity<List<AvailableSlotResponse>> getAvailableSlots(
            @RequestParam("booking_date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate bookingDate,
            @RequestParam("service_id") Integer serviceId
    ) {
        return ResponseEntity.ok(checkoutService.getAvailableSlots(bookingDate, serviceId));
    }

    @PostMapping("/checkout")
    @PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
    public ResponseEntity<TransactionSummaryResponse> checkout(
            @Valid @RequestBody CheckoutRequest request,
            Authentication authentication,
            HttpServletRequest httpServletRequest
    ) {
        return ResponseEntity.ok(checkoutService.checkout(request, authentication.getName(), httpServletRequest));
    }

    @PatchMapping("/{bookingId}/cancel")
    @PreAuthorize("hasAnyAuthority('ROLE_CUSTOMER', 'ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<String>> cancelBooking(
            @PathVariable Integer bookingId,
            Authentication authentication) {
        String role = authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .filter(a -> a.startsWith("ROLE_"))
                .map(a -> a.substring(5))
                .findFirst().orElse("CUSTOMER");
        bookService.updateStatusWithOwnershipCheck(bookingId, "CANCELLED", authentication.getName(), role);
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully", "CANCELLED"));
    }

    @PatchMapping("/{bookingId}/status")
    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<String>> updateBookingStatus(
            @PathVariable Integer bookingId,
            @RequestBody Map<String, String> body,
            Authentication authentication) {
        String status = body.get("status");
        if (status == null || status.isBlank()) {
            return ResponseEntity.badRequest().body(ApiResponse.error("Status is required"));
        }
        String role = authentication.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .filter(a -> a.startsWith("ROLE_"))
                .map(a -> a.substring(5))
                .findFirst().orElse("CUSTOMER");
        bookService.updateStatusWithOwnershipCheck(bookingId, status.toUpperCase(), authentication.getName(), role);
        return ResponseEntity.ok(ApiResponse.success("Status updated", status.toUpperCase()));
    }

}
