package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoPacienteDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
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
        if (maybePaciente.isEmpty()) {
            throw new NotFoundException("El paciente " + id + " no existe.");
        }
        return maybePaciente.get();
    }

    public Paciente recuperarPacientePorDni(Long dni) {
        Optional<Paciente> maybePaciente = pacienteDAO.findByDni(dni);
        if (maybePaciente.isEmpty()) {
            throw new NotFoundException("El paciente con DNI " + dni + " no existe.");
        }
        return maybePaciente.get();
    }

    public List<Paciente> recuperarPacientesPorDniONombreSimilar(String dniONombre) {
        return pacienteDAO.findByDniLikeOrNombreLikeOrderedByDni(dniONombre);
    }

    public void guardarPaciente(NuevoPacienteDTO nuevoPacienteDTO) {
        if (nuevoPacienteDTO == null || nuevoPacienteDTO.hasNullProperties()) {
            throw new BadRequestException("Es necesario contar con todas las propiedades del paciente para crearlo.");
        }
        Paciente nuevoPaciente = new Paciente(nuevoPacienteDTO);
        pacienteDAO.save(nuevoPaciente);
    }
}
