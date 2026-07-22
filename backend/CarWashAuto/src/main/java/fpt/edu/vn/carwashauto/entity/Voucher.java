package com.autowashpro.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "vouchers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Voucher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "promotion_id")
    private Promotion promotion;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "voucher_code", nullable = false, length = 50)
    private String voucherCode;

    @Column(name = "discount_value", precision = 10, scale = 2)
    private BigDecimal discountValue;

    @Column(name = "expired_date")
    private LocalDateTime expiredDate;

    @Column(length = 20)
    @Builder.Default
    private String status = "ACTIVE"; // ACTIVE, USED, EXPIRED
}