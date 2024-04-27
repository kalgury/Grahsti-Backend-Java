package com.example.grahstibackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grahstibackend.dtos.LoginUserDto;
import com.example.grahstibackend.dtos.RegisterUserDto;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.responses.LoginResponse;
import com.example.grahstibackend.services.AuthSerivce;
import com.example.grahstibackend.services.JwtService;

import jakarta.validation.Valid;

// import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final JwtService jwtService;
    private final AuthSerivce authSerivce;

    public AuthController(JwtService jwtService, AuthSerivce authSerivce) {
        this.jwtService = jwtService;
        this.authSerivce = authSerivce;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@Valid @RequestBody RegisterUserDto body) {
        User registeredUser = authSerivce.register(body);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginUserDto body) {
        User authenticatedUser = authSerivce.authenticate(body);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken)
                .setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
