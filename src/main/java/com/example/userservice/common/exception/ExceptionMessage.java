package com.example.userservice.common.exception;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ExceptionMessage {
    private LocalDateTime date;
    private String message;
    private String description;
}
