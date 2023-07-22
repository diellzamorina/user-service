package com.example.userservice.service;

import com.example.userservice.common.dto.UserResponseDTO;
import com.example.userservice.common.dto.UserSignInDTO;
import com.example.userservice.model.User;

public interface UserService {

    UserResponseDTO getProfile(String email);

    UserResponseDTO signIn(UserSignInDTO userSignInDTO);

    UserResponseDTO register(User user);

    UserResponseDTO updateProfile(UserResponseDTO user);
}
