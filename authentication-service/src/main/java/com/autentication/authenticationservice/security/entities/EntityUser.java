package com.autentication.authenticationservice.security.entities;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "users")
public class EntityUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false, nullable = false)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private String username;

    @NotBlank
    @Column(nullable = false)
    private String password;


    public static AppUser toDto(EntityUser entityUser) {
        return AppUser.builder()
                .id(entityUser.getId())
                .userName(entityUser.getUsername())
                .password(entityUser.getPassword())
                .build();
    }

    public static EntityUser fromDto(AppUser appUser) {
        return EntityUser.builder()
                .id(appUser.getId())
                .username(appUser.getUsername())
                .password(appUser.getPassword())
                .build();
    }
}
