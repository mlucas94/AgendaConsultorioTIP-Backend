package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.repositories.PacienteDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteService {

    @Autowired
    private PacienteDAO pacienteDAO;

    public Paciente recuperarPaciente(Long id) {
        Optional<Paciente> maybePaciente = pacienteDAO.findById(id);
        return maybePaciente.get();
    }

    public Paciente recuperarPacientePorDni(Long dni) {
        Optional<Paciente> maybePaciente = pacienteDAO.findByDni(dni);
        return maybePaciente.get();
    }

    public List<Paciente> recuperarPacientePorDniSimilar(Long dni) {
        return pacienteDAO.findByDniLikeOrderedByDni(dni);
    }
}
