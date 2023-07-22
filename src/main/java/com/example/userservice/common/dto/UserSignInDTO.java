package com.example.userservice.common.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserSignInDTO {
    private String email;
    private String password;
}
