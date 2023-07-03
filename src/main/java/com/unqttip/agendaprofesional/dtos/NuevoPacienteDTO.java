package com.unqttip.agendaprofesional.dtos;

import java.util.Objects;

public class NuevoPacienteDTO {
    private Long dni;
    private String nombre;
    private String email;
    private Long telefono;
    private Integer edad;
    private String obraSocial;
    private String plan;

    public Long getDni() {
        return dni;
    }

    public void setDni(Long dni) {
        this.dni = dni;
    }

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

    public Long getTelefono() {
        return telefono;
    }

    public void setTelefono(Long telefono) {
        this.telefono = telefono;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public Boolean hasNullProperties() {
        return (dni == null || nombre == null || email == null || telefono == null || edad == null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NuevoPacienteDTO that = (NuevoPacienteDTO) o;
        return Objects.equals(dni, that.dni) && Objects.equals(nombre, that.nombre) && Objects.equals(email, that.email) && Objects.equals(telefono, that.telefono) && Objects.equals(edad, that.edad) && Objects.equals(obraSocial, that.obraSocial) && Objects.equals(plan, that.plan);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dni, nombre, email, telefono, edad, obraSocial, plan);
    }

    public static NuevoPacienteDTOBuilder builder() {
        return new NuevoPacienteDTOBuilder();
    }

    public static final class NuevoPacienteDTOBuilder {
        private NuevoPacienteDTO nuevoPacienteDTO;

        private NuevoPacienteDTOBuilder() {
            nuevoPacienteDTO = new NuevoPacienteDTO();
        }

        public NuevoPacienteDTOBuilder dni(Long dni) {
            nuevoPacienteDTO.setDni(dni);
            return this;
        }

        public NuevoPacienteDTOBuilder nombre(String nombre) {
            nuevoPacienteDTO.setNombre(nombre);
            return this;
        }

        public NuevoPacienteDTOBuilder email(String email) {
            nuevoPacienteDTO.setEmail(email);
            return this;
        }

        public NuevoPacienteDTOBuilder telefono(Long telefono) {
            nuevoPacienteDTO.setTelefono(telefono);
            return this;
        }

        public NuevoPacienteDTOBuilder edad(Integer edad) {
            nuevoPacienteDTO.setEdad(edad);
            return this;
        }

        public NuevoPacienteDTOBuilder obraSocial(String obraSocial) {
            nuevoPacienteDTO.setObraSocial(obraSocial);
            return this;
        }

        public NuevoPacienteDTOBuilder plan(String plan) {
            nuevoPacienteDTO.setPlan(plan);
            return this;
        }

        public NuevoPacienteDTO build() {
            return nuevoPacienteDTO;
        }
    }
}
