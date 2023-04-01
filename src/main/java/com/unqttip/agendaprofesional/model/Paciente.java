package com.unqttip.agendaprofesional.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private Long dni;

    private String nombre;
    private String email;
    private Integer telefono;
    private Integer edad;

    //TODO: foreign keys
    private String obraSocial;
    private String plan;

    @OneToMany //TODO: agregar (mappedBy="paciente") después de modificar turno
    private List<Turno> turnos;

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
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

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(List<Turno> turnos) {
        this.turnos = turnos;
    }

    // Builder
    public static PacienteBuilder builder() {
        return new PacienteBuilder();
    }

    public static final class PacienteBuilder {
        private Paciente paciente;

        private PacienteBuilder() {
            paciente = new Paciente();
        }

        public PacienteBuilder id(Long id) {
            paciente.setId(id);
            return this;
        }

        public PacienteBuilder dni(Long dni) {
            paciente.setDni(dni);
            return this;
        }

        public PacienteBuilder nombre(String nombre) {
            paciente.setNombre(nombre);
            return this;
        }

        public PacienteBuilder email(String email) {
            paciente.setEmail(email);
            return this;
        }

        public PacienteBuilder telefono(Integer telefono) {
            paciente.setTelefono(telefono);
            return this;
        }

        public PacienteBuilder edad(Integer edad) {
            paciente.setEdad(edad);
            return this;
        }

        public PacienteBuilder obraSocial(String obraSocial) {
            paciente.setObraSocial(obraSocial);
            return this;
        }

        public PacienteBuilder plan(String plan) {
            paciente.setPlan(plan);
            return this;
        }

        public PacienteBuilder turnos(List<Turno> turnos) {
            paciente.setTurnos(turnos);
            return this;
        }

        public Paciente build() {
            return paciente;
        }
    }
}
