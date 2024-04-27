package com.example.grahstibackend.services;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    private final CommonService commonService;

    public AuthSerivce(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            CommonService commonService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.commonService = commonService;
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
        // create a new User
        User user = new User()
                .setEmail(body.getEmail())
                .setPassword(passwordEncoder.encode(body.getPassword()))
                .setFullName(body.getFullName())
                .setMobileNumber(body.getMobileNumber());

        return userRepository.save(user);
    }

    public User authenticate(LoginUserDto data) {
        User userDetails;
        if (commonService.isValidEmail(data.getUsername())) {
            userDetails = userRepository.findByEmail(data.getUsername())
                    .orElseThrow();
        } else {
            userDetails = userRepository.findByMobileNumber(Long.parseLong(data.getUsername()))
                    .orElseThrow();
        }
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDetails.getId(),
                        data.getPassword()));
        return userDetails;
    }

    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return (User) authentication.getPrincipal();
    }

    // TODO: create change password and forget password functionalities.
}
