package com.autentication.authenticationservice.security;


import lombok.Data;

@Data
public class UserCredentials {

    private String userName;
    private String password;
}
