package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.dto.response.BookingResponse;
import com.autowashpro.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_STAFF')")
public class StaffBookingController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings(Authentication authentication) {
        List<BookingResponse> results = bookService.findAllByStaff(authentication.getName());
        return ResponseEntity.ok(ApiResponse.success("Bookings retrieved successfully", results));
    }

    @GetMapping("/date")
    public ResponseEntity<List<BookingResponse>> getBookingsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Authentication authentication) {
        List<BookingResponse> results = bookService.findByStaffAndDate(authentication.getName(), date);
        return ResponseEntity.ok(results);
    }

    @GetMapping("/date-debug")
    public ResponseEntity<List<BookingResponse>> getBookingsByDateDebug(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date,
            Authentication authentication) {
        List<BookingResponse> results = bookService.findByStaffAndDate(authentication.getName(), date);
        return ResponseEntity.ok(results);
    }

    @PatchMapping("/{bookingId}/cancel")
    public ResponseEntity<ApiResponse<String>> cancelBooking(
            @PathVariable Integer bookingId,
            Authentication authentication) {
        bookService.updateStatusWithOwnershipCheck(bookingId, "CANCELLED", authentication.getName(), "STAFF");
        return ResponseEntity.ok(ApiResponse.success("Booking cancelled successfully", "CANCELLED"));
    }

    @PostMapping("/{bookingId}/no-show")
    public ResponseEntity<ApiResponse<String>> markNoShow(
            @PathVariable Integer bookingId,
            Authentication authentication) {
        bookService.updateStatusWithOwnershipCheck(bookingId, "NO_SHOW", authentication.getName(), "STAFF");
        return ResponseEntity.ok(ApiResponse.success("Booking marked as no-show", "NO_SHOW"));
    }
}
