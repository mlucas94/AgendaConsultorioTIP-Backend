package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.FormularioRespondidoDTO;
import com.unqttip.agendaprofesional.dtos.NuevaRespuestaDTO;
import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.dtos.PreguntaRespondidaDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.*;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import com.unqttip.agendaprofesional.repositories.PreguntaDAO;
import com.unqttip.agendaprofesional.repositories.RespuestaDAO;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormularioService {
    private FormularioDAO formularioDAO;
    private PreguntaDAO preguntaDAO;
    private RespuestaDAO respuestaDAO;
    private PacienteService pacienteService;

    public void guardarNuevoFormulario(NuevoFormularioDTO nuevoFormularioDTO) {
        Formulario nuevoFormulario = nuevoFormularioDTO.formularioFromDTO();
        nuevoFormulario.getPreguntas().forEach(pregunta -> {
            validarPregunta(pregunta);
            pregunta.setFormulario(nuevoFormulario);
        });
        formularioDAO.save(nuevoFormulario);
    }

    private void validarPregunta(Pregunta pregunta) {
        if (pregunta.getTipoDeRespuesta() == TipoDeRespuesta.MULTIRESPUESTA && !pregunta.tieneOpciones()) {
            throw new BadRequestException("Las preguntas multirespuesta deben tener opciones");
        }
    }

    public List<Formulario> recuperarPlantillasFormulario() {
        return formularioDAO.findAll();
    }

    public void guardarRespuestas(List<NuevaRespuestaDTO> nuevaRespuestaDTOList) {
        List<Respuesta> respuestaList = nuevaRespuestaDTOList.stream().map(this::respuestaDesdeDTO).collect(Collectors.toList());

        respuestaDAO.saveAll(respuestaList);
    }

    private Respuesta respuestaDesdeDTO(NuevaRespuestaDTO nuevaRespuestaDTO) {
        return Respuesta.builder()
                .respuestaTexto(nuevaRespuestaDTO.getRespuestaTexto())
                .pregunta(preguntaDAO.findById(nuevaRespuestaDTO.getPreguntaId()).get())
                .paciente(pacienteService.recuperarPaciente(nuevaRespuestaDTO.getPacienteId()))
                .build();
    }

    public List<Formulario> recuperarFormulariosRespondidos(Long idPaciente) {
        Set<Formulario> formularios = new HashSet<>();
        List<Respuesta> respuestasPaciente = respuestaDAO.findByPacienteId(idPaciente);
        respuestasPaciente.forEach(respuesta -> {
            formularios.add(respuesta.getPregunta().getFormulario());
        });
        return new ArrayList<>(formularios);
    }

    public FormularioRespondidoDTO recuperarFormularioConRespuestas(Long idPaciente, Long idFormulario) {
        Optional<Formulario> maybeFormulario = formularioDAO.findById(idFormulario);
        if (maybeFormulario.isEmpty()) {
            throw new NotFoundException("Formulario " + idFormulario + " no encontrado");
        }
        Formulario formulario = maybeFormulario.get();
        Paciente paciente = pacienteService.recuperarPaciente(idPaciente);

        return FormularioRespondidoDTO.builder()
                .idFormulario(idFormulario)
                .idPaciente(idPaciente)
                .tituloFormulario(formulario.getTitulo())
                .preguntasRespondidas(this.generarPreguntasRespondidas(formulario.getPreguntas(), idPaciente))
                .build();
    }

    private List<PreguntaRespondidaDTO> generarPreguntasRespondidas(List<Pregunta> preguntas, Long idPaciente) {
        List<PreguntaRespondidaDTO> preguntasRespondidas = new ArrayList<>();
        preguntas.forEach(pregunta -> {
            Optional<Respuesta> maybeRespuesta = respuestaDAO.findByPacienteAndPregunta(idPaciente, pregunta.getId());
            if (maybeRespuesta.isPresent()) {
                Respuesta respuesta = maybeRespuesta.get();
                preguntasRespondidas.add(
                        PreguntaRespondidaDTO.builder()
                                .nombre(pregunta.getPreguntaNombre())
                                .obligatoria(pregunta.getObligatoria())
                                .tipoDeRespuesta(pregunta.getTipoDeRespuesta())
                                .opciones(pregunta.getOpciones() == null ? "" : pregunta.getOpciones())
                                .respuesta(respuesta.getRespuestaTexto())
                                .build()
                );
            }
        });
        return preguntasRespondidas;
    }
}
