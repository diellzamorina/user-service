package com.example.userservice.common.exception;

import com.google.common.base.Throwables;
import org.modelmapper.internal.bytebuddy.implementation.bytecode.Throw;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<?> handleException (RuntimeException ex) {
        ex.printStackTrace();
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setDate(LocalDateTime.now());
        exceptionMessage.setMessage("Something went wrong");
        exceptionMessage.setDescription(ex.getMessage());
        return new ResponseEntity<>(exceptionMessage, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CredentialsException.class)
    public ResponseEntity<?> handleCredentialsException (RuntimeException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setDate(LocalDateTime.now());
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setDescription(Throwables.getStackTraceAsString(ex));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<?> handleUserNotFoundException (RuntimeException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setDate(LocalDateTime.now());
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setDescription(Throwables.getStackTraceAsString(ex));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = UserAlreadyExistsException.class)
    public ResponseEntity<?> handleUserAlreadyExistsException (RuntimeException ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setDate(LocalDateTime.now());
        exceptionMessage.setMessage(ex.getMessage());
        exceptionMessage.setDescription(Throwables.getStackTraceAsString(ex));
        return new ResponseEntity<>(exceptionMessage, HttpStatus.FORBIDDEN);
    }

}
