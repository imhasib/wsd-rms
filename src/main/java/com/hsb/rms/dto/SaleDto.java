package com.hsb.rms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hsb.rms.domain.Item;
import com.hsb.rms.domain.Order;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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
    private Long unitPrice;
    private Long total;
    private ItemDto item;


}
