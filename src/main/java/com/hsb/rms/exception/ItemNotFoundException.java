package com.hsb.rms.exception;

import org.springframework.http.HttpStatus;


public class ItemNotFoundException extends AbstractException {

    public ItemNotFoundException() {
        super("Item not found.");
        setStatus(HttpStatus.NOT_FOUND);
    }

    public ItemNotFoundException(Long id) {
        super("Item not found with ID: " + id);
    }
}
