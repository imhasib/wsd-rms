package com.hsb.rms.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class ErrorResponse {
    @Getter
    private int statusCode;
    @Getter
    private String message;
}
