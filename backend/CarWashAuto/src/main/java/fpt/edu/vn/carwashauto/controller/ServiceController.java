package com.autowashpro.controller;

import com.autowashpro.dto.request.ServiceRequest;
import com.autowashpro.dto.response.ServiceResponse;
import com.autowashpro.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/service")
@RequiredArgsConstructor
public class ServiceController {
    private final BookingService bookingService;

    @GetMapping
    public ResponseEntity<List<ServiceResponse>> findAllPublic() {
        return ResponseEntity.ok(bookingService.findAllServices());
    }

    @GetMapping("/me")
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<List<ServiceResponse>> findAll() {
        return ResponseEntity.ok(bookingService.findAllServices());
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceResponse> create(@RequestBody ServiceRequest serviceRequest) {
        return ResponseEntity.ok(bookingService.bookService(serviceRequest));
    }

    @PutMapping("/{serviceId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceResponse> update(@RequestBody ServiceRequest serviceRequest, @PathVariable Integer serviceId) {
        return ResponseEntity.ok(bookingService.updateService(serviceRequest, serviceId));
    }

    @DeleteMapping("/{serviceId}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Integer serviceId) {
        bookingService.deleteService(serviceId);
        return ResponseEntity.ok("Deleted");
    }

    @PatchMapping("/{serviceId}/activate")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceResponse> activate(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(bookingService.activateService(serviceId));
    }

    @PatchMapping("/{serviceId}/deactivate")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ServiceResponse> deactivate(@PathVariable Integer serviceId) {
        return ResponseEntity.ok(bookingService.deactivateService(serviceId));
    }
}
