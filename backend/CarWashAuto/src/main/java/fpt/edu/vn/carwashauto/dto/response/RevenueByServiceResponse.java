package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueByServiceResponse {
    private Integer serviceId;
    private String serviceName;
    private long bookingCount;
    private BigDecimal totalRevenue;
}
