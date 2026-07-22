package com.autowashpro.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Entity
@Table(name = "services")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "service_name", nullable = false, length = 100 , columnDefinition = "NVARCHAR(100)")
    private String serviceName;

    @Column(length = 500 , columnDefinition = "NVARCHAR(500)" )
    private String description;

    @Column(nullable = false)
    private Integer duration; // phút: 15, 30, 60

    @Column(name = "base_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    @Column(name = "base_points", nullable = false)
    private Integer basePoints; // 40, 150, 300

    @Column(length = 20)
    @Builder.Default
    private String status = "ACTIVE";
}