package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    public void enviarMail() {
        //this.emailService.enviarMailNuevoTurno();
    }
}
