package com.istateca.app.constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {

    // To validate the password
    public static boolean isPasswordSecure(String password) {
        // Check if the password is at least 8 characters long.
        if (password.length() < 8) {
            return false;
        }
        // Check if the password contains at least one uppercase letter.
        if (!password.matches(".*[A-Z].*")) {
            return false;
        }
        // Check if the password contains at least one lowercase letter.
        if (!password.matches(".*[a-z].*")) {
            return false;
        }
        // Check if the password contains at least one digit.
        if (!password.matches(".*[0-9].*")) {
            return false;
        }
        // Check if the password contains at least one special character.
        if (!password.matches(".*[!@#$%^&*()_+].*")) {
            return false;
        }
        return true;
    }

    public static boolean verifyEmail(String email) {
        String regex = "^[a-zA-Z0-9._%+-]+@tecazuay\\.edu\\.ec$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
