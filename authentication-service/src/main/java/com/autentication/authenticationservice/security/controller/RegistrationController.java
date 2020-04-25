package com.autentication.authenticationservice.security.controller;


import com.autentication.authenticationservice.security.service.UserRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class RegistrationController {


@Autowired
UserRepositoryService userRepositoryService;

    @GetMapping("/registration/username/{username}/password/{password}")
    public void registration(@PathVariable String username, @PathVariable String password){


        if(userRepositoryService.findIfUsernameFree(username)){
            userRepositoryService.saveCredentials(username,password);
        }
    }

}
