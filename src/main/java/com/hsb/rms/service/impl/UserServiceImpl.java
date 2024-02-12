package com.hsb.rms.service.impl;

import com.hsb.rms.domain.User;
import com.hsb.rms.dto.AccountDto;
import com.hsb.rms.dto.UserDto;
import com.hsb.rms.exception.EmailExistException;
import com.hsb.rms.exception.UserNotFoundException;
import com.hsb.rms.repository.UserRepository;
import com.hsb.rms.service.UserService;
import com.hsb.rms.service.mapper.AccountMapper;
import com.hsb.rms.service.mapper.UserMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AccountMapper accountMapper;

    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper, AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountMapper = accountMapper;
    }

    @Override
    public UserDto save(AccountDto accountDto) {
        if(findOneByEmail(accountDto.getEmail()).isPresent()) {
            throw new EmailExistException(accountDto.getEmail());
        }
        User user = accountMapper.toEntity(accountDto);
        user = userRepository.save(user);
        log.info("User created:" + user.getEmail());
        return userMapper.toDto(user);
    }

    @Override
    public Optional<UserDto> update(UserDto userDto) {
        return userRepository
                .findById(userDto.getId())
                .map(userRepository::save)
                .map(userMapper::toDto);
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        log.info("Retrieving all of the registered customer for page: {}", pageable.getOffset());
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDto> findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }
        return user.map(userMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    public Optional<User> findOneByEmail(String email) {
        Optional<User> user = userRepository.findOneByEmail(email);
        return user;
    }

}
