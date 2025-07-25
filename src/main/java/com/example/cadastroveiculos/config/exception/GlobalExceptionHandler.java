package com.example.cadastroveiculos.config.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResponseStatusException.class)
    public ErrorResponseDTO handleResponseStatusException(ResponseStatusException ex, HttpServletRequest request) {
        return new ErrorResponseDTO(
            ex.getStatusCode().value(),
            ex.getReason(),
            request.getRequestURI()
        );
    }
}
