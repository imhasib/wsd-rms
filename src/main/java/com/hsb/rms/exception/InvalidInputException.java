package com.hsb.rms.exception;

public class InvalidInputException extends AbstractException {
    public InvalidInputException(String inputName) {
        super("Invalid input: " + inputName);
    }
}
