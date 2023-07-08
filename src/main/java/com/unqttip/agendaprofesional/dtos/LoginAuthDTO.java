package com.unqttip.agendaprofesional.dtos;

public class LoginAuthDTO {
    private String email;
    private String nombreUsuario;
    private String accessToken;

    public LoginAuthDTO() { }

    public LoginAuthDTO(String email, String nombreUsuario, String accessToken) {
        this.email = email;
        this.nombreUsuario = nombreUsuario;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}