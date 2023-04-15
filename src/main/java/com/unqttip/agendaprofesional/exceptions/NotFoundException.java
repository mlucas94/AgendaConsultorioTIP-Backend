package com.unqttip.agendaprofesional.exceptions;

public class NotFoundException extends RuntimeException {
    private static String DESCRIPTION = "Not found exception";

    public NotFoundException(String detail) {
        super(DESCRIPTION = detail);
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
