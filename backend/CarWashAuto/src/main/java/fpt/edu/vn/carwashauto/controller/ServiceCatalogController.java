package com.autowashpro.controller;

import com.autowashpro.dto.response.ServiceCatalogResponse;
import com.autowashpro.service.CheckoutService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/services")
@RequiredArgsConstructor
public class ServiceCatalogController {
    private final CheckoutService checkoutService;

    @GetMapping
    public ResponseEntity<List<ServiceCatalogResponse>> getActiveServices() {
        return ResponseEntity.ok(checkoutService.getActiveServices());
    }
}
