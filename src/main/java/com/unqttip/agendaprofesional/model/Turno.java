package com.unqttip.agendaprofesional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime horarioInicio;
    @JsonFormat(pattern = "dd-MM-yyyy hh:mm")
    private LocalDateTime horarioFin;
    private String tipo;

    //TODO: @ManyToOne
    @ManyToOne(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;

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

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
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

        public TurnoBuilder idPaciente(Paciente paciente) {
            turno.setPaciente(paciente);
            return this;
        }

        public Turno build() {
            return turno;
        }
    }
}
