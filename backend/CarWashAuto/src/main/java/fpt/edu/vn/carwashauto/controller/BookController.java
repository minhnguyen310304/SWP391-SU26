package com.autowashpro.controller;

import com.autowashpro.dto.request.BookingRequest;
import com.autowashpro.dto.response.BookingResponse;
import com.autowashpro.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/book")
@RequiredArgsConstructor
@PreAuthorize("hasAuthority('ROLE_CUSTOMER')")
public class BookController {
    private final BookService bookService;

    @PostMapping()
    public ResponseEntity<BookingResponse> book(@RequestBody BookingRequest bookingRequest) {
        return ResponseEntity.ok(bookService.book(bookingRequest));
    }

    @GetMapping("/me")
    public ResponseEntity<List<BookingResponse>> findAll(Authentication authentication) {
        return ResponseEntity.ok(bookService.findAllByCustomer(authentication.getName()));
    }
}
