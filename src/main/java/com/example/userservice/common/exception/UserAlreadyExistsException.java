package com.example.userservice.common.exception;

public class UserAlreadyExistsException extends RuntimeException{
    public UserAlreadyExistsException (String message) {
        super(message);
    }
}
