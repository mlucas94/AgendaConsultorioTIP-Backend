package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@EnableAutoConfiguration
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/turnos/{id}")
    public ResponseEntity<Turno> recuperarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.recuperarTurno(id));
    }

    @GetMapping("/turnos")
    public ResponseEntity<List<Turno>> recuperarTurnos() {
        return ResponseEntity.ok(turnoService.recuperarTurnos());
    }

    @PostMapping("/turnos")
    public void crearTurno(@RequestBody NuevoTurnoDTO turnoDTO) {
        this.turnoService.guardarTurno(turnoDTO);
    }
}
