package com.unqttip.agendaprofesional.dtos;

import com.unqttip.agendaprofesional.model.Turno;

import java.util.List;

public class LandingDTO {
    private Integer cantidadTurnosDia;
    private Turno proximoTurnoPrioritario;
    private List<Turno> proximosTurnos;

    public Integer getCantidadTurnosDia() {
        return cantidadTurnosDia;
    }

    public void setCantidadTurnosDia(Integer cantidadTurnosDia) {
        this.cantidadTurnosDia = cantidadTurnosDia;
    }

    public Turno getProximoTurnoPrioritario() {
        return proximoTurnoPrioritario;
    }

    public void setProximoTurnoPrioritario(Turno proximoTurnoPrioritario) {
        this.proximoTurnoPrioritario = proximoTurnoPrioritario;
    }

    public List<Turno> getProximosTurnos() {
        return proximosTurnos;
    }

    public void setProximosTurnos(List<Turno> proximosTurnos) {
        this.proximosTurnos = proximosTurnos;
    }
}
