package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ServiceResponse {
    private Integer id;
    private String serviceName;
    private String description;
    private Integer base_points;
    private Integer duration;
    private BigDecimal price;
    private String status;
}
