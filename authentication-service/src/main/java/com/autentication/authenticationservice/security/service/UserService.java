package com.autentication.authenticationservice.security.service;

import com.autentication.authenticationservice.security.entities.AppUser;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Optional;


public interface UserService extends UserDetailsService {

    Optional<AppUser> findByUsername(String username);

    AppUser createUser(AppUser appUser);


}