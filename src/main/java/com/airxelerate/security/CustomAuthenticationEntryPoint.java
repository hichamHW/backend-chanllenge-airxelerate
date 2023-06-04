package com.airxelerate.security;

import com.airxelerate.dto.response.MessageErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;


@Slf4j
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
    private final ObjectMapper objectMapper;

    public CustomAuthenticationEntryPoint(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException) throws UsernameNotFoundException, IOException {
        log.error("Unauthorized error: {}", authException.getClass());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        MessageErrorResponse errorResponse;
        if (authException instanceof BadCredentialsException) {
            errorResponse = MessageErrorResponse.builder()
                    .dateTime(LocalDateTime.now())
                    .messages(Collections.singleton("Invalid credentials"))
                    .path(request.getPathInfo())
                    .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
                    .build();
            objectMapper.writeValue(response.getOutputStream(), errorResponse);
        }
        if(authException instanceof LockedException) {
            errorResponse = MessageErrorResponse.builder()
                    .dateTime(LocalDateTime.now())
                    .messages(Collections.singleton("Account locked"))
                    .path(request.getPathInfo())
                    .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
                    .build();
            objectMapper.writeValue(response.getOutputStream(), errorResponse);
        }
//        if(authException instanceof InsufficientAuthenticationException) {
//            errorResponse = MessageErrorResponse.builder()
//                    .dateTime(LocalDateTime.now())
//                    .messages(Collections.singleton(authException.getMessage()))
//                    .path(request.getPathInfo())
//                    .statusCode(HttpServletResponse.SC_UNAUTHORIZED)
//                    .build();
//            objectMapper.writeValue(response.getOutputStream(), errorResponse);
//        }
    }
}

