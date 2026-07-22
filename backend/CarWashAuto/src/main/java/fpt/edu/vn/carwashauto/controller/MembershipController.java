package com.autowashpro.controller;

import com.autowashpro.dto.request.MembershipRequest;
import com.autowashpro.dto.response.MembershipResponse;
import com.autowashpro.service.MembershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/membership")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @GetMapping
    public ResponseEntity<List<MembershipResponse>> findAll() {
        return ResponseEntity.ok(membershipService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MembershipResponse> findById(@PathVariable Integer id) {
        return ResponseEntity.ok(membershipService.findById(id));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MembershipResponse> create(@RequestBody MembershipRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(membershipService.create(request));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<MembershipResponse> update(@PathVariable Integer id, @RequestBody MembershipRequest request) {
        return ResponseEntity.ok(membershipService.update(id, request));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<String> delete(@PathVariable Integer id) {
        membershipService.delete(id);
        return ResponseEntity.ok("Membership deleted successfully");
    }
}
