package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.services.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class EmailController {
    @Autowired
    private EmailService emailService;

    @PostMapping("/send-email")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public void enviarMail() {
        //this.emailService.enviarMailNuevoTurno();
    }
}
