package com.hsb.rms.exception;

public class EmailExistException extends AbstractException {
    public EmailExistException(String email) {
        super("Email already taken: " + email);
    }
}
