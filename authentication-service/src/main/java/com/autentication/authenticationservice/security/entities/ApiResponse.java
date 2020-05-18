package com.autentication.authenticationservice.security.entities;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Data
@AllArgsConstructor
public class ApiResponse {

    private int status;
    private String message;
    private Object result;


    public String toJson() throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        try {
            return ow.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            log.error(e.getLocalizedMessage());
            throw e;
        }
    }

    public void send(HttpServletResponse response, int code) throws IOException {
        response.setStatus(code);
        response.setContentType("application/json");
        String errorMessage = toJson();

        response.getWriter().println(errorMessage);
        response.getWriter().flush();
    }
}
