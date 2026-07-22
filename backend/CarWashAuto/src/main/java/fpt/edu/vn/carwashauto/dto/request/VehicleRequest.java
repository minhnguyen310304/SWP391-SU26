package com.autowashpro.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequest {
    @NotBlank(message = "License plate is required")
    @Pattern(
            regexp = "(?i)^[0-9]{2}[A-Z]{1,2}-?[0-9]{3}\\.?[0-9]{2}$",
            message = "License plate must be a valid car license plate"
    )
    private String licensePlate;

    @NotBlank(message = "Model is required")
    @Size(min = 50, max = 100, message = "Model must be between 50 and 100 characters")
    private String model;

    @NotBlank(message = "Brand is required")
    @Size(min = 50, max = 100, message = "Brand must be between 50 and 100 characters")
    private String brand;

}
