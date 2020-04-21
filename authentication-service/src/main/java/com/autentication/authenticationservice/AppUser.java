package com.autentication.authenticationservice;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AppUser {

    private int id;
    private String userName;
    private String password;
    private String userRole;

}
