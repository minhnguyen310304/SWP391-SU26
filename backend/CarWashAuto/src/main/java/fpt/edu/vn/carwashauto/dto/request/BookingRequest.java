package com.autowashpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookingRequest {
    private Integer vehicleId;
    private Integer serviceId;
    private LocalTime startDate;
    private LocalTime endDate;
    // Optional booking date sent by client
    private LocalDate bookingDate;

}
