package com.autowashpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
    private String orderId;
    private long amount;
    private String orderInfo;
    private String voucherCode;
    private Integer customerId;
    private String memberRank;
}
