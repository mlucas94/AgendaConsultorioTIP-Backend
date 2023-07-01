package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevaRespuestaDTO;
import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.model.Pregunta;
import com.unqttip.agendaprofesional.model.Respuesta;
import com.unqttip.agendaprofesional.model.TipoDeRespuesta;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import com.unqttip.agendaprofesional.repositories.PreguntaDAO;
import com.unqttip.agendaprofesional.repositories.RespuestaDAO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FormularioService {
    private FormularioDAO formularioDAO;
    private PreguntaDAO preguntaDAO;
    private RespuestaDAO respuestaDAO;
    private PacienteService pacienteService;

    public void guardarNuevoFormulario(NuevoFormularioDTO nuevoFormularioDTO) {
        Formulario nuevoFormulario = nuevoFormularioDTO.formularioFromDTO();
        nuevoFormulario.getPreguntas().forEach(this::validarPregunta);
        formularioDAO.save(nuevoFormulario);
    }

    private void validarPregunta(Pregunta pregunta) {
        if (pregunta.getTipoDeRespuesta() == TipoDeRespuesta.MULTIRESPUESTA && !pregunta.tieneOpciones()) {
            throw new BadRequestException("Las preguntas multirespuesta deben tener opciones");
        }
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
}
