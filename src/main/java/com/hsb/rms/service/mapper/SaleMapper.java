package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.dto.SaleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SaleMapper extends DtoMapper<SaleDto, Sale> {
}
