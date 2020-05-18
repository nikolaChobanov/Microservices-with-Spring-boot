package com.autentication.authenticationservice.security.entities;


import lombok.Data;

@Data
public class UserCredentials {

    private String userName;

    private String password;
}
