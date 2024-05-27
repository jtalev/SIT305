package com.example.personallearningapp.utils;

import org.apache.commons.validator.routines.EmailValidator;

public class Util {
    public static Boolean isValidEmail(String email) {
        EmailValidator validator = EmailValidator.getInstance();
        return validator.isValid(email);
    }

    public static Boolean isValidPassword(String password) {
        return password != null && password.length() >= 8;
    }
}
