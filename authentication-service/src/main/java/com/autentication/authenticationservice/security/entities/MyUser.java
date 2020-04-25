package com.autentication.authenticationservice.security.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@Table(name = "USERS")
public class MyUser extends BaseEntity{


    @Column(nullable=false, unique = true)
    private String username;

    private String password;

    private List<GrantedAuthority> userRole;
}
