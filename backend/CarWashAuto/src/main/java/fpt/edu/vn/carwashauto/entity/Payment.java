package com.autowashpro.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne
    @JoinColumn(name = "booking_id", nullable = false)
    private Booking booking;

    @Column(precision = 10, scale = 2)
    private BigDecimal amount;

    @Column(name = "payment_method", length = 30)
    private String paymentMethod; // CASH, ONLINE

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "transaction_ref", length = 50)
    private String transactionRef;

    @Column(name = "gateway_transaction_no", length = 50)
    private String gatewayTransactionNo;

    @Column(name = "bank_code", length = 20)
    private String bankCode;

    @Column(name = "response_code", length = 10)
    private String responseCode;

    @Column(name = "discount_amount", precision = 10, scale = 2)
    private BigDecimal discountAmount;

    @Column(name = "voucher_code", length = 50)
    private String voucherCode;

    @Column(length = 20)
    @Builder.Default
    private String status = "PENDING";
}
