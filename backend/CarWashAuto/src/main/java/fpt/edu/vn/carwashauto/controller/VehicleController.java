package com.autowashpro.controller;

import com.autowashpro.dto.request.VehicleRequest;
import com.autowashpro.dto.response.VehicleRespnse;
import com.autowashpro.service.VehicleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
public class VehicleController {
    private final VehicleService vehicleService;

    @PostMapping
    public ResponseEntity<VehicleRespnse> saveVehicle(@Valid @RequestBody VehicleRequest vehicleRequest, Authentication authentication) {
        return ResponseEntity.ok(vehicleService.saveVehicle(vehicleRequest, authentication.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<VehicleRespnse> updateVehicle(@Valid @RequestBody VehicleRequest vehicleRequest, @PathVariable Integer id, Authentication authentication) {
        return ResponseEntity.ok(vehicleService.updateVehicle(vehicleRequest, id, authentication.getName()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteVehicle(@RequestBody VehicleRequest vehicleRequest, @PathVariable Integer id, Authentication authentication) {
        vehicleService.deleteVehicle(id, authentication.getName());
        return ResponseEntity.ok("Vehicle deleted successfully");
    }
}
