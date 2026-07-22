package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerProfileResponse {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private Integer totalPoint;
    private String tierName;
}
