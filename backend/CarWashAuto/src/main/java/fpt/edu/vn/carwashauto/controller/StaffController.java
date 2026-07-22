package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.dto.response.StaffResponse;
import com.autowashpro.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/staffs")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class StaffController {
    private final StaffService staffService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<StaffResponse>>> getAllStaffs() {
        List<StaffResponse> staffs = staffService.findAllStaffs();
        return ResponseEntity.ok(ApiResponse.success("Staffs retrieved successfully", staffs));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<StaffResponse>> createStaff(@RequestBody Map<String, String> body) {
        StaffResponse staff = staffService.createStaff(
                body.get("username"),
                body.get("password"),
                body.get("email"),
                body.get("phone"),
                body.get("fullName"),
                body.get("staffType")
        );
        return ResponseEntity.ok(ApiResponse.success("Staff created successfully", staff));
    }

    @PatchMapping("/{staffId}/deactivate")
    public ResponseEntity<ApiResponse<String>> deactivateStaff(@PathVariable Integer staffId) {
        staffService.deactivateStaff(staffId);
        return ResponseEntity.ok(ApiResponse.success("Staff deactivated successfully", "INACTIVE"));
    }
}
