package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.FormularioCompletableDTO;
import com.unqttip.agendaprofesional.dtos.NuevaRespuestaDTO;
import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.services.FormularioService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class FormularioController {
    private FormularioService formularioService;

    @PostMapping("/formularios/crear")
    public void crearFormulario(@RequestBody NuevoFormularioDTO nuevoFormularioDTO) {
        formularioService.guardarNuevoFormulario(nuevoFormularioDTO);
    }

    @GetMapping("/formularios")
    public List<FormularioCompletableDTO> recuperarFormularios() {
        return formularioService.recuperarPlantillasFormulario();
    }

    @PostMapping("/formularios/responder")
    public void agregarRespuestas(@RequestBody List<NuevaRespuestaDTO> nuevaRespuestaDTOList) {
        formularioService.guardarRespuestas(nuevaRespuestaDTOList);
    }

    @GetMapping("/formularios/completados/{idPaciente}")
    public List<Formulario> recuperarFormulariosRespondidos(@PathVariable Long idPaciente) {
        return formularioService.recuperarFormulariosRespondidos(idPaciente);
    }
}
