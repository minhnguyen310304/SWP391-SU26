package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.entity.Staff;
import com.autowashpro.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/notifications")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_STAFF')")
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getNotifications(Authentication authentication) {
        Staff staff = notificationService.resolveStaff(authentication.getName());
        if (staff == null) {
            return ResponseEntity.ok(ApiResponse.success("No notifications", List.of()));
        }
        List<Map<String, Object>> notifs = notificationService.getNotifications(staff.getId());
        return ResponseEntity.ok(ApiResponse.success("Notifications retrieved", notifs));
    }

    @GetMapping("/unread-count")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getUnreadCount(Authentication authentication) {
        Staff staff = notificationService.resolveStaff(authentication.getName());
        long count = staff != null ? notificationService.getUnreadCount(staff.getId()) : 0;
        return ResponseEntity.ok(ApiResponse.success("Unread count", Map.of("count", count)));
    }

    @PatchMapping("/{notificationId}/read")
    public ResponseEntity<ApiResponse<String>> markAsRead(@PathVariable Integer notificationId) {
        notificationService.markAsRead(notificationId);
        return ResponseEntity.ok(ApiResponse.success("Marked as read", "OK"));
    }

    @PatchMapping("/read-all")
    public ResponseEntity<ApiResponse<String>> markAllAsRead(Authentication authentication) {
        Staff staff = notificationService.resolveStaff(authentication.getName());
        if (staff != null) {
            notificationService.markAllAsRead(staff.getId());
        }
        return ResponseEntity.ok(ApiResponse.success("All marked as read", "OK"));
    }
}
