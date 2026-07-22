package com.autowashpro.dto.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckinRequest {
    @JsonAlias({"BookingId", "booking_id"})
    private Integer bookingId;
    @JsonAlias({"ServiceId", "service_id"})
    private Integer serviceId;
    @JsonAlias({"VehicleId", "vehicle_id"})
    private Integer vehicleId;
}
