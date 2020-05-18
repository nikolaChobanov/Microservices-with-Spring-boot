package com.autentication.authenticationservice.security.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class RegistrationData {

    private static String username;
    private static String password;

    public RegistrationData(String userName, String password) {
        this.username = userName;
        this.password = password;

    }

    public static String getUsername() {
        return username;
    }

    public static String getPassword() {
        return password;
    }
}
