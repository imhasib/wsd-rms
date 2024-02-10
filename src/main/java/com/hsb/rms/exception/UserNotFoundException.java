package com.hsb.rms.exception;

import org.springframework.http.HttpStatus;

public class UserNotFoundException extends AbstractException {

    public UserNotFoundException() {
        super("User not found.");
        setStatus(HttpStatus.NOT_FOUND);
    }

    public UserNotFoundException(Long id) {
        super("User not found with ID: " + id);
        setStatus(HttpStatus.NOT_FOUND);
    }
}
