package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.NuevoFormularioDTO;
import com.unqttip.agendaprofesional.model.Formulario;
import com.unqttip.agendaprofesional.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

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
}
