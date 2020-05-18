package com.autentication.authenticationservice.security.controller;


import com.autentication.authenticationservice.security.entities.AppUser;
import com.autentication.authenticationservice.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/register")
public class RegistrationController {



    @Autowired
    UserService userService;


    @PostMapping(
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public AppUser createUser(@Valid @RequestBody AppUser appUser) {

        return userService.createUser(appUser);
    }


}
