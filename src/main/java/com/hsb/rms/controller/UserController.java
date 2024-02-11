package com.hsb.rms.controller;

import com.hsb.rms.domain.User;
import com.hsb.rms.dto.AccountDto;
import com.hsb.rms.dto.UserDto;
import com.hsb.rms.exception.InvalidInputException;
import com.hsb.rms.exception.UserNotFoundException;
import com.hsb.rms.service.UserService;
import com.hsb.rms.service.mapper.UserMapper;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("")
    public ResponseEntity<UserDto> createUser(@Valid @RequestBody AccountDto accountDto) {
        UserDto userDto = userService.save(accountDto);
        return new ResponseEntity<>(userDto, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}", consumes = {"application/json", "application/merge-patch+json"})
    public ResponseEntity<UserDto> updateUser(
            @PathVariable(value = "id", required = false) final Long id,
            @NotNull @Valid @RequestBody UserDto userDto
    ) {
        if (userDto.getId() == null) {
            throw new UserNotFoundException();
        }

        if (!Objects.equals(id, userDto.getId())) {
            new InvalidInputException("id");
        }
        userService.findOne(id);
        Optional<UserDto> result = userService.update(userDto);

        return ResponseEntity.ok(result.get());
    }

    @GetMapping("")
    public ResponseEntity<Page<UserDto>> getUsers(Pageable pageable) {
        Page<UserDto> userPage = userService.findAll(pageable);
        return new ResponseEntity<>(userPage, HttpStatus.OK);
    }

    /**
     * {@code GET  /users/:id} : get the "id" user.
     *
     * @param id the id of the userDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the userDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@NotNull @PathVariable("id") Long id) {
        Optional<UserDto> userDto = userService.findOne(id);
        return ResponseEntity.ok(userDto.get());
    }

    /**
     * {@code DELETE  /users/:id} : delete the "id" user.
     *
     * @param id the id of the userDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable("id") Long id) {
        userService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
