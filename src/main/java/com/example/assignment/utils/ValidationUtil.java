package com.example.assignment.utils;

public class ValidationUtil {

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{6,}$";

    private static final String PHONE_REGEX = "\\d{10}";

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static void validateEmpty(String validateObject, String error){
        if(validateObject == null){
            throw new IllegalArgumentException(error);
        }
    }

    public static void validatePhoneNumber (String phoneNumber, String error){
        if(!phoneNumber.matches(PHONE_REGEX)){
            throw new IllegalArgumentException(error);
        }
    }

    public static void validateEmail (String email, String error){
        if(!email.matches(EMAIL_REGEX)){
            throw new IllegalArgumentException(error);
        }
    }

    public static void validatePassword (String password, String error){
        if(!password.matches(PASSWORD_REGEX)){
            throw new IllegalArgumentException(error);
        }
    }

    public static void validateNull (Object object, String error){
        if (object == null){
            throw new IllegalArgumentException(error);
        }
    }



}
