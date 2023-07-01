package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.NuevaRespuestaDTO;
import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.services.FormularioService;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class FormularioController {
    private FormularioService formularioService;

    @PostMapping("/formularios/crear")
    public void crearFormulario(@RequestBody NuevoFormularioDTO nuevoFormularioDTO) {
        formularioService.guardarNuevoFormulario(nuevoFormularioDTO);
    }

    @PostMapping("/formularios/responder")
    public void agregarRespuestas(@RequestBody List<NuevaRespuestaDTO> nuevaRespuestaDTOList) {
        formularioService.guardarRespuestas(nuevaRespuestaDTOList);
    }
}
