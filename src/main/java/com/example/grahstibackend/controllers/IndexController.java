package com.example.grahstibackend.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.security.PermitAll;

@RestController
@RequestMapping("")
public class IndexController {
    @GetMapping("/healthCheck")
    @PermitAll
    public String signup() {
        return "healthCheck";
    }

}
