package com.example.grahstibackend.services;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class CommonService {

    private static final String EMAIL_PATTERN = "^[\\w!#$%&'*+/=?^`{|}~-]+(?:\\.[\\w!#$%&'*+/=?^`{|}~-]+)*@(?:[A-Z0-9-]+\\.)+[A-Z]{2,6}$";
    private static final Pattern EMAIL_REGEX = Pattern.compile(EMAIL_PATTERN, Pattern.CASE_INSENSITIVE);

    private static final String MOBILE_NUMBER_PATTERN = "^[789]\\d{9}$"; // Adjust for specific country codes if needed

    public boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) {
            return false; // Consider handling empty emails differently if needed
        }
        Matcher matcher = EMAIL_REGEX.matcher(email);
        return matcher.find();
    }

    public boolean isValidMobileNumber(String mobileNumber) {
        if (mobileNumber == null || mobileNumber.isBlank()) {
            return false; // Consider handling empty mobile numbers differently if needed
        }
        return mobileNumber.matches(MOBILE_NUMBER_PATTERN);
    }

}
