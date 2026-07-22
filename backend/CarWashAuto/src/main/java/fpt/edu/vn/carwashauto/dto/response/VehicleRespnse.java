package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRespnse {
    private String licensePlate;
    private String model;
    private String brand;
}
