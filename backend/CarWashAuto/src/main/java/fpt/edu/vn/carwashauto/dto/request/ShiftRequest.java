package com.autowashpro.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShiftRequest {
    private String shiftName;
    private LocalTime startTime;
    private LocalTime endTime;
}
