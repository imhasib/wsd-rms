package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Item;
import com.hsb.rms.dto.ItemDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ItemMapper extends DtoMapper<ItemDto, Item>{
}
