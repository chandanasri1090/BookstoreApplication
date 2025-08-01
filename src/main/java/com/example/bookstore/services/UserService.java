package com.example.bookstore.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.bookstore.models.RegisterUserRequest;
import com.example.bookstore.models.UserResponse;
import com.example.bookstore.models.Users;
import com.example.bookstore.repositories.UserDetailsRepository;

@Service
public class UserService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserDetailsRepository userDetailsRepository) {
        this.userDetailsRepository = userDetailsRepository;
    }

    public UserResponse registerUser(RegisterUserRequest userRequest) {
        if (userDetailsRepository.findByUserName(userRequest.getUsername()).isPresent()) {
            throw new RuntimeException("User Already Exists");
        }
        Users user = new Users();
        user.setUserName(userRequest.getUsername());
        user.setPassword(passwordEncoder.encode(userRequest.getPassword()));
        user.setRole(userRequest.getRole());
        Users savedUser = userDetailsRepository.save(user);
        return new UserResponse(savedUser.getId(), savedUser.getUsername());
    }

}
