package com.example.backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class ByteConversionException extends RuntimeException {
    public ByteConversionException(String message) {
        super(message);
    }
}