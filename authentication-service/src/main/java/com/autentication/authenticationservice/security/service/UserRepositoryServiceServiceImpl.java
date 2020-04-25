package com.autentication.authenticationservice.security.service;

import com.autentication.authenticationservice.security.entities.MyUser;
import com.autentication.authenticationservice.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Slf4j
@Service
public class UserRepositoryServiceServiceImpl implements UserRepositoryService {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean findIfUsernameFree(String username) {
        return userRepository.findByUsername(username)==null;
    }

    @Override
    public void saveCredentials(String username, String password) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Collection<GrantedAuthority> authorities = (Collection<GrantedAuthority>) authentication.getAuthorities();
        List<GrantedAuthority> listAuthorities=new ArrayList<>();
        listAuthorities.addAll(authorities);
        userRepository.save(new MyUser(username,password,listAuthorities));
    }


}
