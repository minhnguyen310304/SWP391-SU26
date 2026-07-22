package com.autowashpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StaffShiftRequest {
    private Integer staffId;
    private Integer shiftId;
    private LocalDate workDate;
}
