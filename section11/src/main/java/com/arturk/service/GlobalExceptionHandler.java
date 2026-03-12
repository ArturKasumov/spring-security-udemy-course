package com.arturk.service;

import com.arturk.utils.TimeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("timestamp", TimeUtils.currentTimeAsString());
        body.put("message",  ex.getMessage());
        body.put("error", HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(body);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, Object>> handleAll(AuthenticationException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.UNAUTHORIZED.value());
        body.put("timestamp", TimeUtils.currentTimeAsString());
        body.put("message",  ex.getMessage());
        body.put("error", HttpStatus.UNAUTHORIZED.getReasonPhrase());

        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, Object>> handleAll(AccessDeniedException ex) {

        Map<String, Object> body = new HashMap<>();
        body.put("status", HttpStatus.FORBIDDEN.value());
        body.put("timestamp", TimeUtils.currentTimeAsString());
        body.put("message",  ex.getMessage());
        body.put("error", HttpStatus.FORBIDDEN.getReasonPhrase());

        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(body);
    }
}