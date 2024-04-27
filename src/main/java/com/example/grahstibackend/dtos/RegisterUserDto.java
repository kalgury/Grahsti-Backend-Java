package com.example.grahstibackend.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class RegisterUserDto {

    @NotNull(message = "Email is required.")
    @Email(message = "Invalid email format.")
    private String email;

    @NotNull(message = "Password is required.")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters.")
    private String password;

    @NotNull(message = "Full Name is required.")
    private String fullName;

    @NotNull(message = "Mobile No is required.")
    @Pattern(regexp = "^[789]\\d{9}$", message = "Enter valid mobile number.")
    private String mobileNumber;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }

    public long getMobileNumber() {
        return Long.parseLong(mobileNumber);
    }

    @Override
    public String toString() {
        return "User{" +
                ", fullName='" + fullName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", mobileNumber=" + mobileNumber +
                '}';
    }
}
