package com.example.userservice.service;

import com.example.userservice.common.dto.UserResponseDTO;
import com.example.userservice.common.dto.UserSignInDTO;
import com.example.userservice.common.exception.CredentialsException;
import com.example.userservice.common.exception.UserAlreadyExistsException;
import com.example.userservice.common.exception.UserNotFoundException;
import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserResponseDTO getProfile(String email) {
        Optional<User> user =  userRepository.findById(email);
        if (user.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }
        else {
            return modelMapper.map(user.get(), UserResponseDTO.class);
        }
    }

    @Override
    public UserResponseDTO signIn(UserSignInDTO userSignInDTO) {
        Optional<User> u = userRepository.findById(userSignInDTO.getEmail());
        if (u.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }
        if (passwordEncoder.matches(userSignInDTO.getPassword(), u.get().getPassword())) {
            return modelMapper.map(u.get(), UserResponseDTO.class);
        }else {
            throw new CredentialsException("Credentials are not correct");
        }
    }

    @Override
    public UserResponseDTO register(User user) {
        if (checkIfUserExists(user.getEmail())) {
            throw new UserAlreadyExistsException("User exists");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return modelMapper.map(userRepository.save(user), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateProfile(UserResponseDTO user) {
        Optional<User> u = userRepository.findById(user.getEmail());
        if (u.isEmpty()) {
            throw new UserNotFoundException("User does not exist");
        }
        u.get().setUsername(user.getUsername());
        return modelMapper.map(userRepository.save(u.get()), UserResponseDTO.class);
    }

    private boolean checkIfUserExists (String email) {
        for (User u : userRepository.findAll()) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }
}
