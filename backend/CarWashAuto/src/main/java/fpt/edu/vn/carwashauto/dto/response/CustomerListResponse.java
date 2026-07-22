package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerListResponse {
    private Integer id;
    private String fullName;
    private String phone;
    private String email;
    private String licensePlate;
    private String tierName;
    private Integer totalPoint;
    private LocalDateTime lastActivityDate;
}
