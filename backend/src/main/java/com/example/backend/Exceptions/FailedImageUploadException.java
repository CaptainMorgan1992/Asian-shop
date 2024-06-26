package com.example.backend.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class FailedImageUploadException extends RuntimeException {
    public FailedImageUploadException(String message) {
        super(message);
    }
}