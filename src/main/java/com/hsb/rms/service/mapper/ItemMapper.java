package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.Item;
import com.hsb.rms.dto.ItemDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper extends DtoMapper<ItemDto, Item>{
}
