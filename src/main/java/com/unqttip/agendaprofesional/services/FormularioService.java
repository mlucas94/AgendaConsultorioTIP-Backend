package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.model.Pregunta;
import com.unqttip.agendaprofesional.model.TipoDeRespuesta;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormularioService {
    @Autowired
    private FormularioDAO formularioDAO;

    public void guardarNuevoFormulario(NuevoFormularioDTO nuevoFormularioDTO) {
        Formulario nuevoFormulario = nuevoFormularioDTO.formularioFromDTO();
        nuevoFormulario.getPreguntas().forEach(pregunta -> {
            validarPregunta(pregunta);
            pregunta.setFormulario(nuevoFormulario);
        });
        formularioDAO.save(nuevoFormulario);
    }

    private void validarPregunta(Pregunta pregunta) {
        if (pregunta.getTipo() == TipoDeRespuesta.MULTISELECT && !pregunta.tieneOpciones()) {
            throw new BadRequestException("Las preguntas multirespuesta deben tener opciones");
        }
    }

    public Formulario recuperarPorId(Long idFormulario) {
        Optional<Formulario> maybeFormulario = formularioDAO.findById(idFormulario);
        if (maybeFormulario.isEmpty()) {
            throw new NotFoundException("Formulario " + idFormulario + " no encontrado");
        }
        return maybeFormulario.get();
    }

    public List<Formulario> recuperarFormularios() {
        return formularioDAO.findAll();
    }
}
