package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.*;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.*;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import com.unqttip.agendaprofesional.repositories.PreguntaDAO;
import com.unqttip.agendaprofesional.repositories.RespuestaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FormularioService {
    @Autowired
    private FormularioDAO formularioDAO;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private PreguntaDAO preguntaDAO;
    @Autowired
    private RespuestaDAO respuestaDAO;

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

    public void responderFormulario(NuevoFormularioRespondidoDTO nuevoFormularioRespondidoDTO) {
        LocalDateTime fechaRespondida = LocalDateTime.now();
        Paciente paciente = pacienteService.recuperarPaciente(nuevoFormularioRespondidoDTO.getIdPaciente());
        Formulario formulario = this.recuperarPorId(nuevoFormularioRespondidoDTO.getIdFormulario());

        nuevoFormularioRespondidoDTO.getNuevasRespuestas().forEach(nuevaRespuestaDTO -> {
            Pregunta preguntaRespondida = recuperarPreguntaPorId(nuevaRespuestaDTO.getIdPregunta());
            Respuesta nuevaRespuesta = Respuesta.builder()
                    .respuestaNombre(nuevaRespuestaDTO.getRespuestaNombre())
                    .pregunta(preguntaRespondida)
                    .formulario(formulario)
                    .paciente(paciente)
                    .fecha(fechaRespondida)
                    .build();
            respuestaDAO.save(nuevaRespuesta);
        });
    }

    private Pregunta recuperarPreguntaPorId(Long idPregunta) {
        Optional<Pregunta> maybePregunta = preguntaDAO.findById(idPregunta);
        if (maybePregunta.isEmpty()) {
            throw new NotFoundException("Pregunta " + idPregunta + " no encontrada");
        }
        return maybePregunta.get();
    }

    public List<PortadaFormularioDTO> recuperarFormulariosRespondidosPorPaciente(Long idPaciente) {
        List<Respuesta> respuestasPaciente = respuestaDAO.findByPaciente(idPaciente);
        List<Formulario> formulariosCompletados = respuestasPaciente.stream().map(Respuesta::getFormulario).distinct().collect(Collectors.toList());
        return formulariosCompletados.stream().map(Formulario::fromModelToPortadaDTO).collect(Collectors.toList());
    }

    public FormularioRespondidoDTO recuperarRespuestasDeUsuarioEnForm(Long idPaciente, Long idFormulario) {
        Formulario formulario = this.recuperarPorId(idFormulario);
        List<Respuesta> respuestas = respuestaDAO.findByPacienteAndFormulario(idPaciente, idFormulario);
        List<PreguntaRespondidaDTO> preguntasRespondidas = respuestas.stream().map(Respuesta::fromModelToDTO).collect(Collectors.toList());
        return FormularioRespondidoDTO.builder()
                .idFormulario(idFormulario)
                .titulo(formulario.getTitulo())
                .idPaciente(idPaciente)
                .preguntasRespondidas(preguntasRespondidas)
                .build();
    }
}
