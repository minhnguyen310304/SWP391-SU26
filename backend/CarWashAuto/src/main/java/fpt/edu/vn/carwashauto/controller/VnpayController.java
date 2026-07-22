package com.autowashpro.controller;

import com.autowashpro.dto.request.PaymentRequest;
import com.autowashpro.dto.response.VnpayReturnResult;
import com.autowashpro.service.CheckoutService;
import com.autowashpro.service.VnpayService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/vnpay")
@RequiredArgsConstructor
public class VnpayController {
    private final VnpayService vnPayService;
    private final CheckoutService checkoutService;

    @PostMapping("/create-payment")
    public ResponseEntity<Map<String, Object>> createPayment(@RequestBody PaymentRequest paymentRequest, HttpServletRequest request) {
        String paymentUrl = vnPayService.createPaymentUrl(paymentRequest, request);

        Map<String, Object> response = new HashMap<>();
        response.put("code", "00");
        response.put("message", "success");
        response.put("data", paymentUrl);
       return ResponseEntity.ok(response);
    }

    @GetMapping("/return")
    public RedirectView handleReturn(@RequestParam Map<String, String> params) {
        VnpayReturnResult result = checkoutService.handleVnpayReturn(params);
        return new RedirectView(result.getRedirectUrl());
    }
}
