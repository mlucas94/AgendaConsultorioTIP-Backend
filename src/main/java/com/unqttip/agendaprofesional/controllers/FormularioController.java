package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.NuevaRespuestaDTO;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.model.FormularioString;
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
    public void crearFormulario(@RequestBody String nuevoFormularioDTO) {
        formularioService.guardarNuevoFormularioString(nuevoFormularioDTO);
    }

    @GetMapping("/formularios")
    public List<FormularioString> recuperarFormularios() {
        return formularioService.recuperarPlantillasFormularioString();
    }

    @GetMapping("/formularios/{idForm}")
    public FormularioString recuperarFormulario(@PathVariable Long idForm) {
        return formularioService.recuperarFormularioStringId(idForm);
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
