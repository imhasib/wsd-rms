package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Order;
import com.hsb.rms.dto.OrderDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface OrderMapper extends DtoMapper<OrderDto, Order> {
}
