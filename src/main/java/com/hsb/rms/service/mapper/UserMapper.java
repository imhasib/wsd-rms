package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.User;
import com.hsb.rms.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.function.Function;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class UserMapper implements DtoMapper<UserDto, User> {
    @Override
    public UserDto toDto(User entity) {
        return new UserDto(entity);
    }
}
