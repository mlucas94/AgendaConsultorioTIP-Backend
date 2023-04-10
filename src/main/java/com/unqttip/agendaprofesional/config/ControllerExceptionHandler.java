package com.unqttip.agendaprofesional.config;

import com.unqttip.agendaprofesional.exceptions.ApiError;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = { BadRequestException.class })
    protected ResponseEntity<ApiError> handleBadRequestException(BadRequestException e) {
        ApiError apiError = new ApiError("bad_request", e.getMessage(), HttpStatus.BAD_REQUEST.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }

    @ExceptionHandler(value = { Exception.class })
    protected ResponseEntity<ApiError> handleGeneralException(Exception e) {
        ApiError apiError = new ApiError("internal_error", e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return ResponseEntity.status(apiError.getStatus())
                .body(apiError);
    }
}
