package com.autentication.authenticationservice.security.block;

import com.autentication.authenticationservice.security.controller.RegistrationController;
import com.autentication.authenticationservice.security.entities.ApiResponse;
import com.autentication.authenticationservice.security.entities.AppUser;
import com.autentication.authenticationservice.security.entities.RegistrationData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.hateoas.Link;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        AuthenticationException e)
            throws IOException, ServletException {

        String errorMessage = ExceptionUtils.getMessage(e);

        sendError(httpServletResponse, HttpServletResponse.SC_UNAUTHORIZED, errorMessage, e);
    }


    private void sendError(HttpServletResponse response, int code, String message, Exception e) throws IOException {
        SecurityContextHolder.clearContext();

        Link link = linkTo(
                methodOn(RegistrationController.class).createUser(new AppUser(1L, RegistrationData.getUsername(), RegistrationData.getPassword())))
                .withRel("If you would like to register these credentials press here");


        ApiResponse exceptionResponse =
                new ApiResponse(HttpServletResponse.SC_UNAUTHORIZED, message, link);


        exceptionResponse.send(response, code);
    }
}