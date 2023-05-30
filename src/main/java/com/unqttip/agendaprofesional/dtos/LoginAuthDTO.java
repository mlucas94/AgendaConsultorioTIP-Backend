package com.unqttip.agendaprofesional.dtos;

public class LoginAuthDTO {
    private String email;
    private String accessToken;

    public LoginAuthDTO() { }

    public LoginAuthDTO(String email, String accessToken) {
        this.email = email;
        this.accessToken = accessToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }
}