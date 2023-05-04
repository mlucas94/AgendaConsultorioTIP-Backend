package com.unqttip.agendaprofesional.dtos;

public class ConsultaTurnosDisponiblesDTO {
    String fechaConsultada;
    String tipoDeTurno;

    public String getFechaConsultada() {
        return fechaConsultada;
    }

    public void setFechaConsultada(String fechaConsultada) {
        this.fechaConsultada = fechaConsultada;
    }

    public String getTipoDeTurno() {
        return tipoDeTurno;
    }

    public void setTipoDeTurno(String tipoDeTurno) {
        this.tipoDeTurno = tipoDeTurno;
    }

    public static ConsultaTurnosDisponiblesDTOBuilder builder() {
        return new ConsultaTurnosDisponiblesDTOBuilder();
    }

    public static final class ConsultaTurnosDisponiblesDTOBuilder {
        private ConsultaTurnosDisponiblesDTO consultaTurnosDisponiblesDTO;

        private ConsultaTurnosDisponiblesDTOBuilder() {
            consultaTurnosDisponiblesDTO = new ConsultaTurnosDisponiblesDTO();
        }

        public ConsultaTurnosDisponiblesDTOBuilder fechaConsultada(String fechaConsultada) {
            consultaTurnosDisponiblesDTO.setFechaConsultada(fechaConsultada);
            return this;
        }

        public ConsultaTurnosDisponiblesDTOBuilder tipoDeTurno(String tipoDeTurno) {
            consultaTurnosDisponiblesDTO.setTipoDeTurno(tipoDeTurno);
            return this;
        }

        public ConsultaTurnosDisponiblesDTO build() {
            return consultaTurnosDisponiblesDTO;
        }
    }
}
