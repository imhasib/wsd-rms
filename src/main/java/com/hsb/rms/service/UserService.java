package com.hsb.rms.service;

import com.hsb.rms.domain.User;
import com.hsb.rms.dto.AccountDto;
import com.hsb.rms.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    /**
     * Save the user.
     *
     * @param accountDto the account to be created.
     * @return the persisted user.
     */
    UserDto save(AccountDto accountDto);

    /**
     * Updates the user.
     *
     * @param user the user to update.
     * @return the persisted user.
     */
    Optional<UserDto> update(UserDto userDto);

    /**
     * Get all the users.
     *
     * @param pageable the pagination information.
     * @return the list of users.
     */
    Page<UserDto> findAll(Pageable pageable);

    /**
     * Get the user by Id.
     *
     * @param id the id of the user.
     * @return the user.
     */
    Optional<UserDto>  findOne(Long id);

    /**
     * Delete the user by Id.
     *
     * @param id the id of the user.
     */
    void delete(Long id);
}
