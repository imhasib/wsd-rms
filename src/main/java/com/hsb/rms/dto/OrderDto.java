package com.hsb.rms.dto;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.domain.enumeration.PayType;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto implements Serializable {
    private Long id;
    private String counter;
    @NotNull
    private Long bill;
    @NotNull
    private PayType paidId;
    private String instruction;
    private UserDto customer;
    private UserDto servedBy;
    private List<SaleDto> sales;
}
