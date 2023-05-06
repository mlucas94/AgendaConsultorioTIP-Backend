package com.unqttip.agendaprofesional.model;

import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;

import java.time.LocalDateTime;

public class RangoDeTurno {
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;
    private Boolean disponible;

    public RangoDeTurno() {}

    public RangoDeTurno(LocalDateTime horaInicio, LocalDateTime horaFin, Boolean disponible) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.disponible = disponible;
    }

    public LocalDateTime getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(LocalDateTime horaInicio) {
        this.horaInicio = horaInicio;
    }

    public LocalDateTime getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(LocalDateTime horaFin) {
        this.horaFin = horaFin;
    }

    public Boolean getDisponible() {
        return disponible;
    }

    public void setDisponible(Boolean disponible) {
        this.disponible = disponible;
    }

    public static RangoDeTurnoDTOBuilder builder() {
        return new RangoDeTurnoDTOBuilder();
    }

    public static final class RangoDeTurnoDTOBuilder {
        private RangoDeTurno rangoDeTurno;

        private RangoDeTurnoDTOBuilder() {
            rangoDeTurno = new RangoDeTurno();
        }

        public RangoDeTurnoDTOBuilder horaInicio(LocalDateTime horaInicio) {
            rangoDeTurno.setHoraInicio(horaInicio);
            return this;
        }

        public RangoDeTurnoDTOBuilder horaFin(LocalDateTime horaFin) {
            rangoDeTurno.setHoraFin(horaFin);
            return this;
        }

        public RangoDeTurnoDTOBuilder disponible(Boolean disponible) {
            rangoDeTurno.setDisponible(disponible);
            return this;
        }

        public RangoDeTurno build() {
            return rangoDeTurno;
        }
    }

    public RangoDeTurnoDTO fromModelObject() {
        RangoDeTurnoDTO rangoDeTurnoDTO = new RangoDeTurnoDTO();
        rangoDeTurnoDTO.setHoraInicio(this.getHoraInicio().toLocalTime().toString());
        rangoDeTurnoDTO.setHoraFin(this.getHoraFin().toLocalTime().toString());
        rangoDeTurnoDTO.setDisponible(this.disponible);
        return rangoDeTurnoDTO;
    }

    public Boolean sigueDisponible(Turno turno) {
        return !(this.horaInicio.isAfter(turno.getHorarioInicio()) && this.horaInicio.isBefore(turno.getHorarioFin()))
                && (!this.horaInicio.isEqual(turno.getHorarioInicio()))
                && !(this.horaFin.isAfter(turno.getHorarioInicio()) && this.horaFin.isBefore(turno.getHorarioFin()))
                && (!this.horaFin.isEqual(turno.getHorarioFin()));
    }
}
