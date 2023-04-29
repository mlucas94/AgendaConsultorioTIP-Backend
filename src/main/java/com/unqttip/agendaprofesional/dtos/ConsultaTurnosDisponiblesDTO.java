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
}
