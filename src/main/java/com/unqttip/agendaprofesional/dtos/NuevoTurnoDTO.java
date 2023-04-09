package com.unqttip.agendaprofesional.dtos;

import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class NuevoTurnoDTO {
    private String tipo;
    private String fecha;
    private String horaInicio;
    private String horaFin;
    private Long paciente;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Long getPaciente() {
        return paciente;
    }

    public void setPaciente(Long paciente) {
        this.paciente = paciente;
    }

    public Turno turnoFromDTO(EntityManager entityManager) {
        Turno turno = new Turno();
        String horaInicioString = this.fecha + " " + this.horaInicio;
        String horaFinString = this.fecha + " " + this.horaFin;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime horarioInicio = LocalDateTime.parse(horaInicioString, formatter);
        LocalDateTime horarioFin = LocalDateTime.parse(horaFinString, formatter);
        turno.setHorarioInicio(horarioInicio);
        turno.setHorarioFin(horarioFin);
        turno.setTipo(this.tipo);
        Paciente pacienteTurno = entityManager.getReference(Paciente.class, this.paciente);
        turno.setPaciente(pacienteTurno);
        return turno;
    }
}
