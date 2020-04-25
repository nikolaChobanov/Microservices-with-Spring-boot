package com.autentication.authenticationservice.security.service;

public interface UserRepositoryService {


    boolean findIfUsernameFree(String username);

    void saveCredentials(String username,String password);

}
