package com.example.bookstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.bookstore.models.AuthRequest;
import com.example.bookstore.util.JWTUtil;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authManager;

    @Autowired
    JWTUtil jwtUtil;

    @GetMapping("/authenticate")
    public String generateToken(@RequestBody AuthRequest authRequest) {
        System.out.println("Request received");
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
            return jwtUtil.generateToken(authRequest.getUsername());
        } catch (Exception e) {
            throw e;
        }
    }
}
