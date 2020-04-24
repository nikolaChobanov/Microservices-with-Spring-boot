package com.autentication.authenticationservice.security.block;

import com.autentication.authenticationservice.security.entities.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler
        implements AuthenticationFailureHandler {

    private ObjectMapper objectMapper = new ObjectMapper();

   /* @Override
    public void onAuthenticationFailure(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException)
            throws IOException, ServletException {

        System.out.println(authException.getLocalizedMessage());
        System.out.println(authException.getCause());
        System.out.println(authException.getSuppressed());
        System.out.println(authException.getMessage());

        response.setStatus(HttpStatus.EXPECTATION_FAILED.value());
        Map<String, Object> data = new HashMap<>();
        data.put(
                "timestamp",
                Calendar.getInstance().getTime());
        data.put(
                "exception",
                authException.getMessage());

        response.getOutputStream()
                .println(objectMapper.writeValueAsString(data));
    }*/

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

        ApiResponse exceptionResponse =
                new ApiResponse(HttpServletResponse.SC_UNAUTHORIZED, message, ExceptionUtils.getStackTrace(e));

        exceptionResponse.send(response, code);
    }
}