package com.unqttip.agendaprofesional.config;

import com.unqttip.agendaprofesional.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    public ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        ApiError apiError = new ApiError("bad_request", e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { NotFoundException.class })
    public ResponseEntity<ApiError> handleNotFoundException(NotFoundException e) {
        ApiError apiError = new ApiError("not_found", e.getMessage(), HttpStatus.NOT_FOUND.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { ForbiddenException.class })
    public ResponseEntity<ApiError> handleForbiddenException(ForbiddenException e) {
        ApiError apiError = new ApiError("forbidden", e.getMessage(), HttpStatus.FORBIDDEN.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { AuthenticationException.class })
    public ResponseEntity<ApiError> handleAuthenticationException(AuthenticationException e) {
        ApiError apiError = new ApiError("unauthorized", e.getMessage(), HttpStatus.UNAUTHORIZED.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { Exception.class })
    public ResponseEntity<ApiError> handleGeneralException(Exception e) {
        ApiError apiError = new ApiError("internal_error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
}
