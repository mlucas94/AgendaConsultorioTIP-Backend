package com.unqttip.agendaprofesional;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableAutoConfiguration
public class TurnoController {

    @GetMapping("/turno/")
    public ResponseEntity<Turno> byTurnoId() throws Exception {
        Turno turno = new Turno();
        turno.setId(1);
        turno.setMensaje("Un mensaje del backend");
        return ResponseEntity.ok(turno);
    }

}
