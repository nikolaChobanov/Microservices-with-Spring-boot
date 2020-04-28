package com.autentication.authenticationservice.security.config;

import com.autentication.authenticationservice.security.block.CustomAuthenticationFailureHandler;
import com.autentication.authenticationservice.security.block.CustomAuthenticationSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityCredentialsConfig extends WebSecurityConfigurerAdapter {


    @Qualifier("userService")
    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtConfig jwtConfig;


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                //Disabled because no need of this type of security when using jwt
                .csrf().disable()
                // Stateless so we don't store the user's state
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()

                //Filter validating user credentials and adding token in the response header
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, successHandler(), failureHandler()))
                .authorizeRequests()
                //allowing all POST requests
                .antMatchers(HttpMethod.POST, jwtConfig.getUri()).permitAll()
                .antMatchers(HttpMethod.POST, "/register").permitAll()
                //all other requests need to be authenticated
                .anyRequest().authenticated();
    }


    //used to fetch user data from database
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    public JwtConfig jwtConfig() {
        return new JwtConfig();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomAuthenticationSuccessHandler successHandler() {
        return new CustomAuthenticationSuccessHandler();
    }

    @Bean
    public CustomAuthenticationFailureHandler failureHandler() {
        return new CustomAuthenticationFailureHandler();
    }


}
