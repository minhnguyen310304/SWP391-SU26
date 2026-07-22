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
public class TransactionSummaryResponse {
    private String transactionRef;
    private String paymentMethod;
    private String paymentStatus;
    private String message;
    private String paymentUrl;
    private String paymentRedirectUrl;
    private String paymentInstructions;
    private Boolean sandbox;
    private String voucherCode;
    private String membershipTier;
    private Integer earnedPoints;
    private BigDecimal originalAmount;
    private BigDecimal discountAmount;
    private BigDecimal totalAmount;
    private List<CheckoutItemResponse> items;
}
