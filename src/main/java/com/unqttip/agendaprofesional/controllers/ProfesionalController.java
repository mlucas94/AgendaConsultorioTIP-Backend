package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.dtos.LoginDTO;
import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/login")
    public LoginDTO login(@RequestBody IntentoDeLoginDTO intentoDeLoginDTO) {
        return profesionalService.login(intentoDeLoginDTO);
    }
}
