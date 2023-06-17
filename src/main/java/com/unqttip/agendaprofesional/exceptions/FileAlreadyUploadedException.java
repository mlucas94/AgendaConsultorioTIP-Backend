package com.unqttip.agendaprofesional.exceptions;

public class FileAlreadyUploadedException extends RuntimeException {
    private static String DESCRIPTION = "Not found exception";

    public FileAlreadyUploadedException(String detail) {
        super(DESCRIPTION = detail);
    }

    @Override
    public String getMessage() {
        return DESCRIPTION;
    }
}
