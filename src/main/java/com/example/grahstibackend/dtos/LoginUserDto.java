package com.example.grahstibackend.dtos;

public class LoginUserDto {

    private String username;
    private String password;

    // getters and setters here...
    // Todo: Add validation rules.
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
