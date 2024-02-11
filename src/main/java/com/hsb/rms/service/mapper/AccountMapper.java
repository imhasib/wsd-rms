package com.hsb.rms.service.mapper;

import com.hsb.rms.domain.User;
import com.hsb.rms.dto.AccountDto;
import com.hsb.rms.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Service;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class AccountMapper implements DtoMapper<AccountDto, User> {

    @Override
    public User toEntity(AccountDto dto) {
        User user = new User();

        user.setLogin(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setEmail(dto.getEmail());
        user.setName(dto.getName());
        user.setActivated(true);

        return user;
    }
}
