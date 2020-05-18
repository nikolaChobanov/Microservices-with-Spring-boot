package com.autentication.authenticationservice.security.exception.handle;


import com.autentication.authenticationservice.security.exception.CustomErrorResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;

@Log4j2
@ControllerAdvice
public class ExceptionController extends ResponseEntityExceptionHandler {


    @ExceptionHandler({ResponseStatusException.class})
    protected ResponseEntity<Object> handleResponseStatusExceptions(
            ResponseStatusException ex, WebRequest request) {
        CustomErrorResponse errorResponse = CustomErrorResponse.builder()
                .errors(Collections.singletonList(ex.getReason()))
                .build();
        return handleExceptionInternal(ex, errorResponse, new HttpHeaders(), ex.getStatus(), request);
    }

}


