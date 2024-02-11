package com.hsb.rms.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SaleDto implements Serializable {
    private Long id;
    private Integer quantity;
    private Double unitPrice;
    private ItemDto item;
}
