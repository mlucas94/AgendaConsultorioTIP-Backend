package com.unqttip.agendaprofesional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "archivos")
public class Archivo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate fechaCarga;

    private String nombreArchivo;

    @ManyToOne(fetch =FetchType.EAGER)
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @JsonIgnore
    @ManyToMany( fetch = FetchType.LAZY )
    private Set<Turno> turnos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    public LocalDate getFechaCarga() {
        return fechaCarga;
    }

    public void setFechaCarga(LocalDate fechaCarga) {
        this.fechaCarga = fechaCarga;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    @JsonIgnore
    public String getPathCompleto() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDate = fechaCarga.format(formatter);
        String pathCompleto = currentDate + "\\" + this.paciente.getId() + "\\" + this.nombreArchivo;
        return pathCompleto;
    }

    public void agregarTurno(Turno turno) {
        this.turnos.add(turno);
    }
}
