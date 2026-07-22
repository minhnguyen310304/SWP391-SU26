package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MembershipResponse {
    private Integer id;
    private String tierName;
    private Integer minPoint;
    private Integer maxBookingDays;
    private Integer discountPercent;
}
