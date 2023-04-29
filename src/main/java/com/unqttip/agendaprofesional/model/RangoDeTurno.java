package com.unqttip.agendaprofesional.model;

import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;

import java.time.LocalDateTime;

public class RangoDeTurno {
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    public RangoDeTurno() {}

    public RangoDeTurno(LocalDateTime horaInicio, LocalDateTime horaFin) {
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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

        public RangoDeTurno build() {
            return rangoDeTurno;
        }
    }

    public RangoDeTurnoDTO fromModelObject() {
        RangoDeTurnoDTO rangoDeTurnoDTO = new RangoDeTurnoDTO();
        rangoDeTurnoDTO.setHoraInicio(this.getHoraInicio().toString());
        rangoDeTurnoDTO.setHoraFin(this.getHoraFin().toString());
        return rangoDeTurnoDTO;
    }
}
