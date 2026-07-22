package com.autowashpro.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "membership_tiers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class MembershipTier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "tier_name", nullable = false, length = 50)
    private String tierName; // MEMBER, SILVER, GOLD, PLATINUM

    @Column(name = "min_point", nullable = false)
    private Integer minPoint; // 0, 500, 1500, 3000

    @Column(name = "max_booking_days", nullable = false)
    private Integer maxBookingDays; // 7, 10, 12, 14

    @Column(name = "discount_percent", nullable = false)
    private Integer discountPercent; // 0, 5, 10, 15
}
