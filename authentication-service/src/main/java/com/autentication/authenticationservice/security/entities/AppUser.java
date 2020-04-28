package com.autentication.authenticationservice.security.entities;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
@NoArgsConstructor
//for easy instantiation
@Builder
@JsonIgnoreProperties({
        "authorities",
        "accountNonExpired",
        "accountNonLocked",
        "credentialsNonExpired",
        "enabled"
})
//to ignore null fields
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppUser implements UserDetails {

    private Long id;

    @NotBlank(message = "username is mandatory")
    @Size(min = 4, max = 20, message = "username must be between 4 and 20 characters")
    private String userName;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank(message = "password is mandatory")
    @Size(min = 5, max = 100, message = "password must be between 5 and 100 characters")
    private String password;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getUsername() {
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
