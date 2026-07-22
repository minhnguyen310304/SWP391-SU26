package com.autowashpro.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "staffs")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Staff {

    @Id
    private Integer id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "full_name", nullable = false, length = 100)
    private String fullName;

    @Column(name = "staff_type", length = 20)
    private String staffType;

    @Column(length = 20)
    @Builder.Default
    private String status = "ACTIVE";
}
