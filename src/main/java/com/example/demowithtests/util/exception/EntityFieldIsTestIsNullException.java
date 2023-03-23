package com.example.demowithtests.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EntityFieldIsTestIsNullException extends RuntimeException {
    public EntityFieldIsTestIsNullException(String message) {
        super(message);
    }
}
