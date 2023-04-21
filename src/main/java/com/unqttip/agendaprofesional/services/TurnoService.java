package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.PacienteDAO;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {

    @Autowired
    private TurnoDAO turnoDAO;
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private EntityManager entityManager;

    public Turno recuperarTurno(Long id) {
        Optional<Turno> maybeTurno = turnoDAO.findById(id);
        if (maybeTurno.isEmpty()) {
            throw new NotFoundException("El turno " + id + " no existe.");
        }
        return maybeTurno.get();
    }

    public List<Turno> recuperarTurnos() {
        return turnoDAO.findAll();
    }

    public void guardarTurno(NuevoTurnoDTO turnoDto) {
        if (turnoDto == null || turnoDto.hasNullProperties()) {
            throw new BadRequestException("Es necesario contar con todas las propiedades del turno para crearlo.");
        }
        if (pacienteDAO.findById(turnoDto.getPaciente()).isEmpty()) {
            throw new NotFoundException("El usuario " + turnoDto.getPaciente() + " no existe.");
        }
        Turno nuevoTurno = turnoDto.turnoFromDTO(entityManager);
        validarTurno(nuevoTurno);
        turnoDAO.save(nuevoTurno);
    }

    private void validarTurno(Turno turno) {
        if (turno.getHorarioFin().isBefore(turno.getHorarioInicio())) {
            throw new BadRequestException("Un turno no puede tener una hora final previa a la hora de inicio.");
        } else if (turnoDAO.findInTheSameHour(turno.getHorarioInicio(), turno.getHorarioFin()) > 0) {
            throw new BadRequestException("Existen turnos guardados en el horario elegido.");
        }
    }
}
