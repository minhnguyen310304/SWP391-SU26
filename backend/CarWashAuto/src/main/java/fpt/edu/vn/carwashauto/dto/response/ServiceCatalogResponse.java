package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ServiceCatalogResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer durationMinutes;
    private BigDecimal basePrice;
    private Integer basePoints;
    private String badge;
    private String themeColor;
    private List<String> features;
}
