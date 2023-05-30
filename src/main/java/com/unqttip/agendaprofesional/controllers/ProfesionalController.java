package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.IntentoDeLoginDTO;
import com.unqttip.agendaprofesional.dtos.LoginAuthDTO;
import com.unqttip.agendaprofesional.services.ProfesionalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@EnableAutoConfiguration
public class ProfesionalController {

    @Autowired
    private ProfesionalService profesionalService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid IntentoDeLoginDTO intentoDeLoginDTO) {
        LoginAuthDTO loginAuthDTO = profesionalService.login(intentoDeLoginDTO);

        return ResponseEntity.ok().body(loginAuthDTO);
    }
}
