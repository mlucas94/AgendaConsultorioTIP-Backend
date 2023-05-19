package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.exceptions.ForbiddenException;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.repositories.ProfesionalDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ProfesionalService {
    @Autowired
    private ProfesionalDAO profesionalDAO;

    private Profesional login(IntentoDeLoginDTO intentoDeLoginDTO) {
        Optional<Profesional> profesional = profesionalDAO.findById(intentoDeLoginDTO.getEmail());

        if (profesional.isEmpty() || !profesional.get().getPassword().equals(intentoDeLoginDTO.getPassword())) {
            throw new ForbiddenException("Usuario o contraseña incorrectos");
        }

        return profesional.get();
    }
}
