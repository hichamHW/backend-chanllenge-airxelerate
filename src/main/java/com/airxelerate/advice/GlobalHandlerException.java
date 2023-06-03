package com.airxelerate.advice;

import com.airxelerate.dto.response.MessageErrorResponse;
import com.airxelerate.exception.EntityNotFoundException;
import com.airxelerate.exception.UserNameExistException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.naming.AuthenticationException;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalHandlerException {

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<MessageErrorResponse> handleUsernameNotFoundException(UsernameNotFoundException ex, WebRequest request) {
        log.error("catch UsernameNotFoundException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }

    @ExceptionHandler(UserNameExistException.class)
    public ResponseEntity<MessageErrorResponse> handleUserNameExistException(UserNameExistException ex, WebRequest request) {
     log.error("catch handleUserNameExistException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<MessageErrorResponse> handleNotFoundException(EntityNotFoundException ex, WebRequest request) {
        log.error("catch handleNotFoundException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.NOT_FOUND.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<MessageErrorResponse> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.error("catch handleAuthenticationException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.NETWORK_AUTHENTICATION_REQUIRED.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }

    @ExceptionHandler(AuthorizationServiceException.class)
    public ResponseEntity<MessageErrorResponse> handleAuthorizationException(AuthorizationServiceException ex, WebRequest request) {
        log.error("catch handleAuthorizationException error: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new MessageErrorResponse(
                Set.of(ex.getMessage()),
                HttpStatus.UNAUTHORIZED.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }

    @ExceptionHandler({MethodArgumentNotValidException.class,IllegalStateException.class})
    public ResponseEntity<MessageErrorResponse> handleBindException(MethodArgumentNotValidException ex, WebRequest request) {
        log.error("catch handleBindException  error: {}", ex.getBindingResult());
        Set<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageErrorResponse(
                errors,
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now(),
                request.getContextPath()
        ));
    }
}
