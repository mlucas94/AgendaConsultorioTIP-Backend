package com.unqttip.agendaprofesional.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@NoArgsConstructor
@Table(name = "turnos")
public class Turno {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime horarioInicio;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime horarioFin;
    private TipoDeTurno tipo;

    //TODO: @ManyToOne
    @ManyToOne(fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="paciente_id", referencedColumnName = "id")
    private Paciente paciente;

    @ManyToMany(mappedBy = "turnos", fetch =FetchType.EAGER,cascade = CascadeType.ALL)
    /*@JoinTable(name="archivo_turno",
            joinColumns = @JoinColumn(name="archivo_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name="turno_id", referencedColumnName = "id"))*/
    private Set<Archivo> archivos;

    private EstadoDeTurno estado;

    public Set<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(Set<Archivo> archivos) {
        this.archivos = archivos;
    }

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

    public TipoDeTurno getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeTurno tipo) {
        this.tipo = tipo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public EstadoDeTurno getEstado() {
        return estado;
    }

    public void setEstado(EstadoDeTurno estado) {
        this.estado = estado;
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

        public TurnoBuilder tipo(TipoDeTurno tipo) {
            turno.setTipo(tipo);
            return this;
        }

        public TurnoBuilder paciente(Paciente paciente) {
            turno.setPaciente(paciente);
            return this;
        }

        public TurnoBuilder archivos(Set<Archivo> archivos) {
            turno.setArchivos(archivos);
            return this;
        }

        public TurnoBuilder estado(EstadoDeTurno estado) {
            turno.setEstado(estado);
            return this;
        }

        public Turno build() {
            return turno;
        }
    }
}
