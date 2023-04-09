package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.model.Turno;
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
    private EntityManager entityManager;

    public Turno recuperarTurno(Long id) {
        Optional<Turno> maybeTurno = turnoDAO.findById(id);
        return maybeTurno.get();
    }

    public List<Turno> recuperarTurnos() {
        return turnoDAO.findAll();
    }

    public void guardarTurno(NuevoTurnoDTO turnoDto) {
        Turno turno = turnoDto.turnoFromDTO(entityManager);
        turnoDAO.save(turno);
    }
}
