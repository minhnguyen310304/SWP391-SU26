package com.autowashpro.controller;

import com.autowashpro.dto.response.ApiResponse;
import com.autowashpro.dto.response.BookingResponse;
import com.autowashpro.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1/admin/bookings")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class AdminBookingController {
    private final BookService bookService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getAllBookings() {
        List<BookingResponse> results = bookService.findAll();
        return ResponseEntity.ok(ApiResponse.success("All bookings retrieved successfully", results));
    }

    @GetMapping("/date")
    public ResponseEntity<ApiResponse<List<BookingResponse>>> getBookingsByDate(
            @RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        List<BookingResponse> results = bookService.findByDate(date);
        return ResponseEntity.ok(ApiResponse.success("Bookings for date retrieved", results));
    }
}
