package com.rehneo.informationsecurity.lab1.auth;

import com.rehneo.informationsecurity.lab1.error.ConflictException;

public class UserAlreadyExistsException extends ConflictException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
