package com.arturk.config.exception;

import com.arturk.utils.TimeUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    private static final HttpStatus FORBIDDEN = HttpStatus.FORBIDDEN;

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        response.setStatus(FORBIDDEN.value());

        Map<String, Object> body = new HashMap<>();
        body.put("status", FORBIDDEN.value());
        body.put("timestamp", TimeUtils.currentTimeAsString());
        body.put("error", FORBIDDEN.getReasonPhrase());
        body.put("message",  accessDeniedException.getMessage());
        body.put("path",  request.getRequestURI());

        response.getWriter().write(new ObjectMapper().writeValueAsString(body));
    }
}
