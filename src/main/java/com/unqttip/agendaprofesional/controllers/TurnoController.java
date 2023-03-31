package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/turno-test/")
    public ResponseEntity<Turno> byTurnoId() throws Exception {
        Turno turno = new Turno();
        turno.setId(1L);
        return ResponseEntity.ok(turno);
    }

    @GetMapping("/turnos/{id}")
    public ResponseEntity<Turno> recuperarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.recuperarTurno(id));
    }

    @GetMapping("/turnos")
    public ResponseEntity<List<Turno>> recuperarTurnos() {
        return ResponseEntity.ok(turnoService.recuperarTurnos());
    }

    @PostMapping("/turnos")
    public void crearTurno() {
        Turno turno = Turno.builder()
                .horarioInicio(LocalDateTime.now())
                .horarioFin(LocalDateTime.now().plusHours(1))
                .idPaciente(123L)
                .tipo("Consulta")
                .build();
        turnoService.guardarTurno(turno);
    }
}
