package com.hsb.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
@Data
@AllArgsConstructor
public class TotalDto implements Serializable {
    private Double total;
}
