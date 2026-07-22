package com.autowashpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MembershipRequest {
    private String tierName;
    private Integer minPoint;
    private Integer maxBookingDays;
    private Integer discountPercent;
}
