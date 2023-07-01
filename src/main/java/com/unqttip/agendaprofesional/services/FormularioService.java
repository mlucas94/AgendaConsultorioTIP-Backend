package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.*;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.*;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import com.unqttip.agendaprofesional.repositories.FormularioStringDAO;
import com.unqttip.agendaprofesional.repositories.PreguntaDAO;
import com.unqttip.agendaprofesional.repositories.RespuestaDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class FormularioService {
    @Autowired
    private FormularioDAO formularioDAO;
    @Autowired
    private PreguntaDAO preguntaDAO;
    @Autowired
    private RespuestaDAO respuestaDAO;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private FormularioStringDAO formularioStringDAO;

    public void guardarNuevoFormulario(NuevoFormularioDTO nuevoFormularioDTO) {
        Formulario nuevoFormulario = nuevoFormularioDTO.formularioFromDTO();
        nuevoFormulario.getPreguntas().forEach(pregunta -> {
            validarPregunta(pregunta);
            pregunta.setFormulario(nuevoFormulario);
        });
        formularioDAO.save(nuevoFormulario);
    }

    public void guardarNuevoFormularioString(String formString) {
        formularioStringDAO.save(FormularioString.builder().form(formString).build());
    }

    private void validarPregunta(Pregunta pregunta) {
        if (pregunta.getTipoDeRespuesta() == TipoDeRespuesta.MULTISELECT && !pregunta.tieneOpciones()) {
            throw new BadRequestException("Las preguntas multirespuesta deben tener opciones");
        }
    }

    public List<FormularioCompletableDTO> recuperarPlantillasFormulario() {
        return formularioDAO.findAll().stream().map(Formulario::fromModelToDTO).collect(Collectors.toList());
    }

    public List<FormularioString> recuperarPlantillasFormularioString() {
        return formularioStringDAO.findAll();
    }

    public FormularioString recuperarFormularioStringId(Long idForm) {
        Optional<FormularioString> maybeFormulario = formularioStringDAO.findById(idForm);
        if (maybeFormulario.isEmpty()) {
            throw new NotFoundException("Formulario " + idForm + " no encontrado");
        }
        return maybeFormulario.get();
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
        List<Respuesta> respuestasPaciente = respuestaDAO.findAll()
                .stream().filter(respuesta -> respuesta.getPaciente().getId().equals(idPaciente)).collect(Collectors.toList());
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
            List<Respuesta> respuestaList = respuestaDAO.findAll();
            respuestaList = respuestaList.stream().filter(
                    respuesta1 -> respuesta1.getPregunta().getId().equals(pregunta.getId())
                    || respuesta1.getPaciente().getId().equals(idPaciente)
            ).collect(Collectors.toList());
            if (!respuestaList.isEmpty()) {
                Respuesta respuesta = respuestaList.get(0);
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
