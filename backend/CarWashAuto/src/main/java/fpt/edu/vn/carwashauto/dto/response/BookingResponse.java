package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingResponse {
    private Integer bookingId;
    private Integer vehicleId;
    private String licensePlate;
    private Integer serviceId;
    private String serviceName;
    private LocalDate bookingDate;
    private LocalTime startTime;
    private LocalTime endTime;
    private String status;
    private BigDecimal totalAmount;

    // Customer info
    private Integer customerId;
    private String customerName;
    private String customerPhone;
    private String customerEmail;

    // Payment details (optional)
    private String paymentMethod;
    private String paymentStatus;
    private java.time.LocalDateTime paymentDate;
    private BigDecimal paymentAmount;
}
