package com.example.demowithtests.util.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.METHOD_NOT_ALLOWED)
public class PassportHasUserException extends RuntimeException {
    public PassportHasUserException () {
        super("Can`t set this passport to user, because was set before");
    }
}
