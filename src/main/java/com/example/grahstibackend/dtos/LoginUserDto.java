package com.example.grahstibackend.dtos;

import com.example.grahstibackend.validations.Username;
import jakarta.validation.constraints.NotNull;

public class LoginUserDto {
    
    @NotNull(message = "Username is required.")
    @Username()
    private String username;

    @NotNull(message = "Password is required.")
    private String password;

    // getters and setters here...
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" +
                // ", fullName='" + fullName + '\'' +
                ", email=" + username + '\'' +
                ", password='" + password + '\'' +
                // ", createdAt=" + createdAt +
                // ", updatedAt=" + updatedAt +
                '}';
    }

}
