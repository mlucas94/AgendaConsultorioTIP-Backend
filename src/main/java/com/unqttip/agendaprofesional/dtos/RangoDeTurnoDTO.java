package com.unqttip.agendaprofesional.dtos;

import java.time.LocalDateTime;

public class RangoDeTurnoDTO {
    private LocalDateTime horaInicio;
    private LocalDateTime horaFin;

    public RangoDeTurnoDTO() {}

    public RangoDeTurnoDTO(LocalDateTime horaInicio, LocalDateTime horaFin) {
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
        private RangoDeTurnoDTO rangoDeTurnoDTO;

        private RangoDeTurnoDTOBuilder() {
            rangoDeTurnoDTO = new RangoDeTurnoDTO();
        }

        public RangoDeTurnoDTOBuilder horaInicio(LocalDateTime horaInicio) {
            rangoDeTurnoDTO.setHoraInicio(horaInicio);
            return this;
        }

        public RangoDeTurnoDTOBuilder horaFin(LocalDateTime horaFin) {
            rangoDeTurnoDTO.setHoraFin(horaFin);
            return this;
        }

        public RangoDeTurnoDTO build() {
            return rangoDeTurnoDTO;
        }
    }
}
