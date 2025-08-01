package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.models.RegisterUserRequest;
import com.example.bookstore.models.UserResponse;
import com.example.bookstore.models.Role;
import com.example.bookstore.services.UserService;

@RestController
@RequestMapping("/bookstore/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registerUser")
    public ResponseEntity<UserResponse> registerUser(@RequestBody RegisterUserRequest registerUserRequest){
        registerUserRequest.setRole(Role.USER);
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }

    @PostMapping("/registerUserByAdmin")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<UserResponse> registerUserByAdmin(@RequestBody RegisterUserRequest registerUserRequest){
        UserResponse userResponse = userService.registerUser(registerUserRequest);
        return ResponseEntity.ok(userResponse);
    }

}
