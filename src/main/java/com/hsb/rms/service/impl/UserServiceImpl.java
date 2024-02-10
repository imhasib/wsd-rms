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
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
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
        logger.info("User created:" + user.getEmail());
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
        logger.info("Fetching User list.");
        return userRepository.findAll(pageable).map(userMapper::toDto);
    }

    @Override
    public Optional<UserDto> findOne(Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            throw new UserNotFoundException(id);
        }

        logger.info("User found:" + user.get().getEmail());
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
