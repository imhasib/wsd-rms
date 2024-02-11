package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Sale;
import com.hsb.rms.dto.SaleDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface SaleMapper extends DtoMapper<SaleDto, Sale> {
}
