package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Order;
import com.hsb.rms.dto.OrderDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper extends DtoMapper<OrderDto, Order> {
}
