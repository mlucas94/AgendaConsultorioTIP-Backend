package com.unqttip.agendaprofesional.unit.config;

import com.unqttip.agendaprofesional.config.ControllerExceptionHandler;
import com.unqttip.agendaprofesional.exceptions.ApiError;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class ControllerExceptionHandlerTest {
    private ControllerExceptionHandler controllerExceptionHandler = new ControllerExceptionHandler();

    @Test
    void handleBadRequestException() {
        ResponseEntity<ApiError> responseEntity = controllerExceptionHandler.handleBadRequestException(new BadRequestException("bad request message"));

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals(400, responseEntity.getStatusCodeValue());
        assertEquals("bad request message", responseEntity.getBody().getMessage());
    }

    @Test
    void handleNotFoundException() {
        ResponseEntity<ApiError> responseEntity = controllerExceptionHandler.handleNotFoundException(new NotFoundException("not found message"));

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals(404, responseEntity.getStatusCodeValue());
        assertEquals("not found message", responseEntity.getBody().getMessage());
    }

    @Test
    void handleGeneralException() {
        ResponseEntity<ApiError> responseEntity = controllerExceptionHandler.handleGeneralException(new Exception());

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals(500, responseEntity.getStatusCodeValue());
    }
}
