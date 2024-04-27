package com.example.grahstibackend.validations;

import com.example.grahstibackend.services.CommonService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UsernameValidator implements ConstraintValidator<Username, String> {

    private final CommonService commonService;

    public UsernameValidator(CommonService commonService) {
        this.commonService = commonService;
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if (username == null || username.isBlank()) {
            return true; // Allow empty usernames (optional, adjust as needed)
        }

        // Check for email format
        if (commonService.isValidEmail(username)) {
            return true;
        }

        // Check for phone number format (adjust based on your needs)
        return commonService.isValidMobileNumber(username);
    }
}
