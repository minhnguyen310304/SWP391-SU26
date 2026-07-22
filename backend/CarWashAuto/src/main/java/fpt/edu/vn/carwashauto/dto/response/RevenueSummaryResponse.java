package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RevenueSummaryResponse {
    private LocalDate fromDate;
    private LocalDate toDate;
    private BigDecimal totalRevenue;
    private long totalBookings;
    private long completedBookings;
    private long cancelledBookings;
    private BigDecimal averageRevenuePerBooking;
    private BigDecimal totalDiscount;
    private List<RevenueByServiceResponse> revenueByService;
    private List<RevenueByDateResponse> revenueByDate;
}
