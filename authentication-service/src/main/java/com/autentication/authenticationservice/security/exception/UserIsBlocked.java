package com.autentication.authenticationservice.security.exception;

public class UserIsBlocked extends RuntimeException {
    public UserIsBlocked(String message) {
        super(message);
    }
}
