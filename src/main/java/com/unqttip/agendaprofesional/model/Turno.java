package com.unqttip.agendaprofesional.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "TURNOS")
public class Turno {

    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime horarioInicio;
    private LocalDateTime horarioFin;
    private String tipo;

    //TODO: @ManyToOne
    private Long idPaciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getHorarioInicio() {
        return horarioInicio;
    }

    public void setHorarioInicio(LocalDateTime horarioInicio) {
        this.horarioInicio = horarioInicio;
    }

    public LocalDateTime getHorarioFin() {
        return horarioFin;
    }

    public void setHorarioFin(LocalDateTime horarioFin) {
        this.horarioFin = horarioFin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public static TurnoBuilder builder() {
        return new TurnoBuilder();
    }

    public static final class TurnoBuilder {
        private Turno turno;

        private TurnoBuilder() {
            turno = new Turno();
        }

        public TurnoBuilder id(Long id) {
            turno.setId(id);
            return this;
        }

        public TurnoBuilder horarioInicio(LocalDateTime horarioInicio) {
            turno.setHorarioInicio(horarioInicio);
            return this;
        }

        public TurnoBuilder horarioFin(LocalDateTime horarioFin) {
            turno.setHorarioFin(horarioFin);
            return this;
        }

        public TurnoBuilder tipo(String tipo) {
            turno.setTipo(tipo);
            return this;
        }

        public TurnoBuilder idPaciente(Long idPaciente) {
            turno.setIdPaciente(idPaciente);
            return this;
        }

        public Turno build() {
            return turno;
        }
    }
}
