package com.autowashpro.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StaffResponse {
    private Integer id;
    private String username;
    private String email;
    private String phone;
    private String fullName;
    private String staffType;
    private String status;
}
