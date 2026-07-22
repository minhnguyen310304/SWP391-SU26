package com.autowashpro.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
    

@Entity
@Table(name = "customers")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Customer {

    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "tier_id")
    private MembershipTier tier;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "total_point", nullable = false)
    @Builder.Default
    private Integer totalPoint = 0;

    @Column(name = "last_activity_date")
    private LocalDateTime lastActivityDate;
}
