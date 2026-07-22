package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemResponse {
    private Integer bookingId;
    private String serviceName;
    private String licensePlate;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String bookingStatus;
    private BigDecimal originalAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
}
