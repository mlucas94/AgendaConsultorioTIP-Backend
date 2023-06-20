package com.unqttip.agendaprofesional.exceptions;

public class ForbiddenException extends RuntimeException {
    private static String DESCRIPTION = "Forbidden exception";

    public ForbiddenException(String detail) {
        super(DESCRIPTION = detail);
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}