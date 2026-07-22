package com.autowashpro.controller;

import com.autowashpro.dto.response.TransactionSummaryResponse;
import com.autowashpro.service.CheckoutService;
import com.autowashpro.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {
    private final CheckoutService checkoutService;
    private final PaymentService paymentService;

    @GetMapping("/transactions/{transactionRef}")
    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<TransactionSummaryResponse> getTransactionSummary(
            @PathVariable String transactionRef,
            Authentication authentication
    ) {
        return ResponseEntity.ok(checkoutService.getTransactionSummary(transactionRef, authentication.getName()));
    }

    @PostMapping("/{bookingId}/pay")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<Map<String, Object>> processStaffPayment(
            @PathVariable Integer bookingId,
            @RequestBody Map<String, String> request,
            Authentication authentication) {
        String paymentMethod = request.get("paymentMethod");
        Map<String, Object> result = paymentService.processStaffPayment(bookingId, paymentMethod, authentication.getName());
        return ResponseEntity.ok(result);
    }
}
