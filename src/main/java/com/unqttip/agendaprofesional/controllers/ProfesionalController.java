package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.model.Profesional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ProfesionalController {

    @Autowired
    private ProfesionalController profesionalController;

    @PostMapping("/login")
    public Profesional login(@RequestBody IntentoDeLoginDTO intentoDeLoginDTO) {
        return profesionalController.login(intentoDeLoginDTO);
    }
}
