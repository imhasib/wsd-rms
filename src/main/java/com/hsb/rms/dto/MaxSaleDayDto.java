package com.hsb.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class MaxSaleDayDto {
    private Instant date;
    private Double total;
}
