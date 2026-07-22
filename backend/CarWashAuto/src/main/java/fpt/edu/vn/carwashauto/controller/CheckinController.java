package com.autowashpro.controller;

import com.autowashpro.dto.request.CheckinRequest;
import com.autowashpro.dto.response.CheckinResponse;
import com.autowashpro.service.CheckinService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/checkin")
@AllArgsConstructor
public class CheckinController {
    private final CheckinService checkinService;
    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_STAFF')")
    public ResponseEntity<CheckinResponse> checkin(@RequestBody CheckinRequest request, Authentication authentication) {
        return ResponseEntity.ok(checkinService.checkin(request, authentication.getName()));
    }
}
