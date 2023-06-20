package com.unqttip.agendaprofesional.dtos;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class NuevoProfesionalDTO {
    @NotNull @Length(min = 5, max = 50)
    private String nombre;
    @NotNull @Email @Length(min = 5, max = 50)
    private String email;
    @NotNull @Length(min = 5, max = 64)
    private String password;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
