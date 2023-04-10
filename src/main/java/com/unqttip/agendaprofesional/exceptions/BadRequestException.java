package com.unqttip.agendaprofesional.exceptions;

public class BadRequestException extends RuntimeException {
    private static String DESCRIPTION = "Bad request exception";

    public BadRequestException(String detail) {
        super(DESCRIPTION = "Bad request exception: " + detail);
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
