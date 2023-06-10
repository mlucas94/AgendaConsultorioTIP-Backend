package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.ConsultaTurnosDisponiblesDTO;
import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;
import com.unqttip.agendaprofesional.model.RangoDeTurno;
import com.unqttip.agendaprofesional.model.TipoDeTurno;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

@RestController
@EnableAutoConfiguration
public class TurnoController {

    @Autowired
    private TurnoService turnoService;

    @GetMapping("/turnos/{id}")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<Turno> recuperarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.recuperarTurno(id));
    }

    @GetMapping("/turnos")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<List<Turno>> recuperarTurnos() {
        return ResponseEntity.ok(turnoService.recuperarTurnos());
    }

    @PostMapping("/turnos")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public void crearTurno(@RequestBody NuevoTurnoDTO turnoDTO) {
        this.turnoService.guardarTurno(turnoDTO);
    }

    @GetMapping("/turnos/horarios-disponibles")
    @CrossOrigin(origins = "http://localhost:3000/", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST, RequestMethod.DELETE})
    public ResponseEntity<List<RangoDeTurnoDTO>> recuperarHorariosDisponibles(@RequestParam String fechaConsultada, @RequestParam String tipoDeTurno) {
        ConsultaTurnosDisponiblesDTO consultaTurnosDisponiblesDTO = new ConsultaTurnosDisponiblesDTO();
        consultaTurnosDisponiblesDTO.setFechaConsultada(fechaConsultada);
        consultaTurnosDisponiblesDTO.setTipoDeTurno(tipoDeTurno);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaConsulta = LocalDate.parse(consultaTurnosDisponiblesDTO.getFechaConsultada(), formatter);
        TipoDeTurno tipoDeTurnoConsulta = TipoDeTurno.valueOf(consultaTurnosDisponiblesDTO.getTipoDeTurno().toUpperCase(Locale.ROOT));
        List<RangoDeTurnoDTO> rangoDeTurnoList = turnoService.recuperarBandasHorariasDisponibles(fechaConsulta, tipoDeTurnoConsulta);
        return ResponseEntity.ok(rangoDeTurnoList);
    }
}
