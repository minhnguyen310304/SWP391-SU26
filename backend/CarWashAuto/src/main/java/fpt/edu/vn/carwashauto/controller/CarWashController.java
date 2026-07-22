package com.autowashpro.controller;

import com.autowashpro.dto.request.CarWashStatusRequest;
import com.autowashpro.dto.request.CarWashTicketRequest;
import com.autowashpro.dto.response.CarWashStatusResponse;
import com.autowashpro.dto.response.CarWashTicketResponse;
import com.autowashpro.service.CarWashService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carwash")
@RequiredArgsConstructor
public class CarWashController {
    private final CarWashService carWashService;

    @PutMapping("/{bookingId}/status")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<CarWashStatusResponse> updateCarWashStatus(
            @PathVariable Integer bookingId,
            @RequestBody CarWashStatusRequest request) {
        return ResponseEntity.ok(carWashService.updateCarWashStatus(bookingId, request));
    }

    @PostMapping("/{bookingId}/ticket")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<CarWashTicketResponse> generateCarWashTicket(
            @PathVariable Integer bookingId,
            @RequestBody CarWashTicketRequest request) {
        return ResponseEntity.ok(carWashService.generateCarWashTicket(bookingId, request));
    }
}
