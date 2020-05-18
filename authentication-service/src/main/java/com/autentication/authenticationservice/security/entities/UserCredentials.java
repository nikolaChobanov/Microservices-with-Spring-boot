package com.autentication.authenticationservice.security.entities;


import lombok.Data;

@Data
//@Builder
public class UserCredentials {

    /*@NotBlank(message = "username is mandatory")
    @Size(min=4 ,max = 20, message = "username must be between 4 and 20 characters")*/
    private String userName;

    /*   @NotBlank(message = "password is mandatory")
       @Size(min = 5, max = 100 , message = "password must be between 5 and 100 characters")*/
    private String password;
}
