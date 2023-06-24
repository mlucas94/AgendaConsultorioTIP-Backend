package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.model.FormularioCompleto;
import com.unqttip.agendaprofesional.services.FormularioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.*;

@RestController
@EnableAutoConfiguration
public class FormularioController {

    @Autowired
    private FormularioService formularioService;

    @PostMapping("/formulario/guardar")
    public void guardarRespuestas(@RequestBody FormularioCompleto formularioCompleto) {
        this.formularioService.guardarRespuestas(formularioCompleto);
    }

    @GetMapping("/formulario")
    public FormularioCompleto getRespuestas(@RequestParam Long idFormulario) {
        return formularioService.getFormularioCompleto(idFormulario);
    }
}
