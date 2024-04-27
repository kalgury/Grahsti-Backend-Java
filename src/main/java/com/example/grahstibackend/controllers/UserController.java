package com.example.grahstibackend.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.services.AuthSerivce;

@RestController
@RequestMapping("/user")
public class UserController {
    private final AuthSerivce authSerivce;

    public UserController(AuthSerivce authSerivce) {
        this.authSerivce = authSerivce;
    }

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser() {
        User currentUser = authSerivce.getAuthenticatedUser();
        return ResponseEntity.ok(currentUser);
    }
}
