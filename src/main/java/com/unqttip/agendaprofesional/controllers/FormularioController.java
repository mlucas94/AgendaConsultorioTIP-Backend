package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.*;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.model.Respuesta;
import com.unqttip.agendaprofesional.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class FormularioController {
    @Autowired
    private FormularioService formularioService;

    @PostMapping("/formularios/crear")
    public void crearFormulario(@RequestBody NuevoFormularioDTO nuevoFormularioDTO) {
        formularioService.guardarNuevoFormulario(nuevoFormularioDTO);
    }

    @GetMapping("/formularios/{idFormulario}")
    public Formulario recuperarFormularioPorId(@PathVariable Long idFormulario) {
        return formularioService.recuperarPorId(idFormulario);
    }

    @GetMapping("/formularios/listado")
    public List<Formulario> recuperarFormularios() {
        return formularioService.recuperarFormularios();
    }

    @PostMapping("/formularios/responder")
    public void responderFormulario(@RequestBody NuevoFormularioRespondidoDTO nuevoFormularioRespondidoDTO) {
        formularioService.responderFormulario(nuevoFormularioRespondidoDTO);
    }

    @GetMapping("/formularios/respondidos/{idPaciente}")
    public List<PortadaFormularioDTO> recuperarFormulariosRespondidosPorPaciente(@PathVariable Long idPaciente) {
        return formularioService.recuperarFormulariosRespondidosPorPaciente(idPaciente);
    }

    @GetMapping("/formularios/respuestas/{idFormulario}")
    public FormularioRespondidoDTO recuperarRespuestasDeUsuarioEnForm(@RequestParam Long idPaciente, @PathVariable Long idFormulario) {
        return formularioService.recuperarRespuestasDeUsuarioEnForm(idPaciente, idFormulario);
    }

    @PostMapping("/formularios/responder/turno")
    public void responderFormularioTurno(@RequestBody NuevoFormularioRespondidoDTO respondidoDTO) {
        formularioService.responderFormularioTurno(respondidoDTO);
    }

    @GetMapping("/formularios/respondidos/turno/{idTurno}")
    public List<PortadaFormularioDTO> recuperarFormulariosRespondidosEnTurno(@PathVariable Long idTurno) {
        return formularioService.recuperarFormulariosRespondidosEnTurno(idTurno);
    }

    @GetMapping("/formularios/respuestas")
    public FormularioRespondidoDTO recuperarRespuestasEnTurnoEnForm(@RequestParam Long idTurno, @PathVariable Long idFormulario) {
        return formularioService.recuperarRespuestasEnTurnoEnForm(idTurno, idFormulario);
    }
}
