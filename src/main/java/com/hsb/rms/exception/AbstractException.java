package com.hsb.rms.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

public class AbstractException extends RuntimeException {
    @Getter
    @Setter
    private HttpStatus status;

    public AbstractException(String message) {
        super(message);
    }
}
