package com.autowashpro.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CheckoutItemRequest {
    private Integer serviceId;

    @NotBlank(message = "License plate is required")
    @Pattern(
            regexp = "(?i)^[0-9]{2}[A-Z]{1,2}-?[0-9]{3}\\.?[0-9]{2}$",
            message = "License plate must be a valid car license plate"
    )
    private String licensePlate;

    @NotBlank(message = "Brand is required")
    @Size(min = 2, max = 50, message = "Brand must be between 2 and 50 characters")
    private String brand;

    @NotBlank(message = "Model is required")
    @Size(min = 2, max = 50, message = "Model must be between 2 and 50 characters")
    private String model;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime startTime;
}
