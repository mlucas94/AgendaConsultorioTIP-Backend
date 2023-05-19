package com.unqttip.agendaprofesional.dtos;

public class LoginDTO {
    private String email;
    private String nombre;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static LoginDTOBuilder builder() {
        return new LoginDTOBuilder();
    }

    public static final class LoginDTOBuilder {
        private LoginDTO loginDTO;

        private LoginDTOBuilder() {
            loginDTO = new LoginDTO();
        }

        public LoginDTOBuilder email(String email) {
            loginDTO.setEmail(email);
            return this;
        }

        public LoginDTOBuilder nombre(String nombre) {
            loginDTO.setNombre(nombre);
            return this;
        }

        public LoginDTOBuilder token(String token) {
            loginDTO.setToken(token);
            return this;
        }

        public LoginDTO build() {
            return loginDTO;
        }
    }
}
