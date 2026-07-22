package com.autowashpro.controller;

import com.autowashpro.service.StaffProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/staff/me")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_STAFF')")
public class StaffProfileController {

    private final StaffProfileService staffProfileService;

    @GetMapping("/profile")
    public ResponseEntity<Map<String, Object>> getProfile(Authentication authentication) {
        return ResponseEntity.ok(staffProfileService.getProfile(authentication.getName()));
    }

    @GetMapping("/stats")
    public ResponseEntity<Map<String, Object>> getStats(Authentication authentication) {
        return ResponseEntity.ok(staffProfileService.getStats(authentication.getName()));
    }
}
