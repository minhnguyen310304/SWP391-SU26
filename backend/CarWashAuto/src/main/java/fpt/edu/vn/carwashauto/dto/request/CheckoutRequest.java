package com.autowashpro.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutRequest {
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate bookingDate;
    private String paymentMethod;
    private String voucherCode;
    private List<@Valid CheckoutItemRequest> items;
}
