package com.autentication.authenticationservice.security.repository;


import com.autentication.authenticationservice.security.entities.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MyUser, Long> {

    MyUser findByUsername(String username);

}
