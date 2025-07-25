package com.example.cadastroveiculos.config.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErrorResponseDTO {
    private int status;
    private String message;
    private String path;
}
