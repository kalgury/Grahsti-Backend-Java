package com.example.grahstibackend.services;

import java.util.Optional;

import org.apache.coyote.BadRequestException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.grahstibackend.dtos.LoginUserDto;
import com.example.grahstibackend.dtos.RegisterUserDto;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.repositories.UserRepository;

@Service
public class AuthSerivce {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthSerivce(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User register(RegisterUserDto body) {
        // Check if user exists by Email
        Optional<User> userExistsByEmail = userRepository.findByEmail(body.getEmail());
        if (userExistsByEmail.isPresent()) {
            throw new RuntimeException("User Exist By email");

        }
        // Check if user exists by Mobile Number
        Optional<User> userExistsByMobileNumber = userRepository.findByMobileNumber(body.getMobileNumber());
        if (userExistsByMobileNumber.isPresent()) {
            throw new RuntimeException("User Exist By mobile number");
        }
        //create a new User
        User user = new User()
                .setEmail(body.getEmail())
                .setPassword(passwordEncoder.encode(body.getPassword()))
                .setFullName(body.getFullName())
                .setMobileNumber(body.getMobileNumber());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto data) {
        System.out.println(data.toString());
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        data.getUsername(),
                        data.getPassword()));
        // can do for phone Number or Username based
        return userRepository.findByEmail(data.getUsername())
                .orElseThrow();
    }

    // TODO: create change password and forget password functionalities.
}
