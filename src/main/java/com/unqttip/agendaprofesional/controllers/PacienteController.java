package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.NuevoPacienteDTO;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.services.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class PacienteController {

    @Autowired
    private PacienteService pacienteService;

    @GetMapping("/pacientes/{id}")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<Paciente> recuperarPaciente(@PathVariable Long id) {
        return ResponseEntity.ok(pacienteService.recuperarPaciente(id));
    }

    @GetMapping("/pacientes/dni/{dni}")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<Paciente> recuperarPacientePorDni(@PathVariable Long dni) {
        return ResponseEntity.ok(pacienteService.recuperarPacientePorDni(dni));
    }

    @GetMapping("/pacientes/dni")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<List<Paciente>> recuperarPacientesPorDniONombreSimilar(@RequestParam String dniONombre) {
        return ResponseEntity.ok(pacienteService.recuperarPacientesPorDniONombreSimilar(dniONombre));
    }

    @PostMapping("/pacientes")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public void guardarPaciente(@RequestBody NuevoPacienteDTO nuevoPacienteDTO) {
        pacienteService.guardarPaciente(nuevoPacienteDTO);
    }
}
