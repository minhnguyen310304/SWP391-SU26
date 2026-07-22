package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.entity.LeaveRequest;
import com.autowashpro.repository.LeaveRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/leave-requests")
@RequiredArgsConstructor
public class LeaveRequestController {
    private final LeaveRequestRepository leaveRequestRepository;

    @GetMapping("/pending")
    @PreAuthorize("hasAnyAuthority('ROLE_STAFF', 'ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getPendingLeaveRequests() {
        List<LeaveRequest> pending = leaveRequestRepository.findByStatus("PENDING");
        List<Map<String, Object>> data = pending.stream().map(lr -> {
            Map<String, Object> map = new HashMap<>();
            map.put("id", lr.getId());
            Map<String, Object> staffMap = new HashMap<>();
            if (lr.getStaff() != null) {
                staffMap.put("id", lr.getStaff().getId());
                staffMap.put("fullName", lr.getStaff().getFullName());
                if (lr.getStaff().getAccount() != null) {
                    staffMap.put("username", lr.getStaff().getAccount().getUsername());
                }
            }
            map.put("staff", staffMap);
            map.put("leaveDate", lr.getLeaveDate() != null ? lr.getLeaveDate().toString() : null);
            map.put("reason", lr.getReason());
            map.put("status", lr.getStatus());
            map.put("createdAt", lr.getCreatedAt() != null ? lr.getCreatedAt().toString() : null);
            return map;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(ApiResponse.success("Pending leave requests retrieved", data));
    }

    @PatchMapping("/{id}/approve")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<String>> approveLeaveRequest(@PathVariable Integer id) {
        LeaveRequest lr = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Leave request not found"));
        lr.setStatus("APPROVED");
        leaveRequestRepository.save(lr);
        return ResponseEntity.ok(ApiResponse.success("Leave request approved", "APPROVED"));
    }

    @PatchMapping("/{id}/reject")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResponse<String>> rejectLeaveRequest(@PathVariable Integer id) {
        LeaveRequest lr = leaveRequestRepository.findById(id)
                .orElseThrow(() -> new org.springframework.web.server.ResponseStatusException(
                        org.springframework.http.HttpStatus.NOT_FOUND, "Leave request not found"));
        lr.setStatus("REJECTED");
        leaveRequestRepository.save(lr);
        return ResponseEntity.ok(ApiResponse.success("Leave request rejected", "REJECTED"));
    }
}
