package com.example.userservice.controller;

import com.example.userservice.common.dto.UserResponseDTO;
import com.example.userservice.common.dto.UserSignInDTO;
import com.example.userservice.model.User;
import com.example.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class UserController{

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/profile/{email}")
    private ResponseEntity<UserResponseDTO> getProfile (@PathVariable String email) {
        return ResponseEntity.ok().body(userService.getProfile(email));
    }

    @PostMapping("/register")
    private ResponseEntity<UserResponseDTO> register (@RequestBody User user) {
        return ResponseEntity.ok().body(userService.register(user));
    }

    @PostMapping("/login")
    private ResponseEntity<UserResponseDTO> signIn (@RequestBody UserSignInDTO user) {
        return ResponseEntity.ok().body(userService.signIn(user));
    }

    @PutMapping("/update")
    private ResponseEntity<UserResponseDTO> updateProfile (@RequestBody UserResponseDTO user) {
        return ResponseEntity.ok().body(userService.updateProfile(user));
    }

}
