package com.unqttip.agendaprofesional.exceptions;

public class UploadFailedException extends RuntimeException {
    private static String DESCRIPTION = "File upload exception";

    public UploadFailedException(String detail) {
        super(DESCRIPTION = detail);
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
