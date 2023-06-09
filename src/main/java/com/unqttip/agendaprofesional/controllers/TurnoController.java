package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.ConsultaTurnosDisponiblesDTO;
import com.unqttip.agendaprofesional.dtos.LandingDTO;
import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;
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
    public ResponseEntity<Turno> recuperarTurno(@PathVariable Long id) {
        return ResponseEntity.ok(turnoService.recuperarTurno(id));
    }

    @GetMapping("/turnos")
    public ResponseEntity<List<Turno>> recuperarTurnosDia(@RequestParam String fecha) {
        return ResponseEntity.ok(turnoService.recuperarTurnosDia(fecha));
    }

    @PostMapping("/turnos")
    public void crearTurno(@RequestBody NuevoTurnoDTO turnoDTO) {
        this.turnoService.guardarTurno(turnoDTO);
    }

    @PostMapping("/turnos/cancelar/{id}")
    public void cancelarTurno(@PathVariable Long id) {
        this.turnoService.cancelarTurno(id);
    }

    @GetMapping("/turnos/horarios-disponibles")
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
    @GetMapping("/turnos/cantidadTotal")
    public ResponseEntity<Integer> cantidadDeTurnosDeUnDia(@RequestParam String fechaConsultada) {
        Integer cantidadDeTurnosTotal = turnoService.cantidadDeTurnosTotalEnDia(fechaConsultada);
        return ResponseEntity.ok(cantidadDeTurnosTotal);
    }

    @GetMapping("/turnos/cantidadTotalPrioritarios")
    public ResponseEntity<Integer> cantidadDeTurnosPrioritariosDeUnDia(@RequestParam String fechaConsultada) {
        Integer cantidadDeTurnosTotal = turnoService.cantidadDeTurnosPrioritariosTotalEnDia(fechaConsultada);
        return ResponseEntity.ok(cantidadDeTurnosTotal);
    }

    @GetMapping("/turnos/prioritariosEnMes")
    public ResponseEntity<List<String>> diasConPrioritariosEnMes(@RequestParam String fechaConsultada) {
        List<String> diasConPrioritarios = turnoService.getDiasConPrioritarios(fechaConsultada);
        return ResponseEntity.ok(diasConPrioritarios);
    }

    @GetMapping("proximo_turno/{id}")
    public ResponseEntity<Turno> proximoTurnoPaciente(@PathVariable Long id) {
        Turno result = turnoService.recuperarProximoTurnoPaciente(id);
        if(result != null){
            return ResponseEntity.ok(result);
        }
        result = null;
        return ResponseEntity.ok(result);

    }

    @GetMapping("/landing")
    public ResponseEntity<LandingDTO> getLanding() {
        LandingDTO result = turnoService.getLanding();
        return ResponseEntity.ok(result);
    }

    @GetMapping("landing/alerta_desplazado/{id}")
    public List<Long> getIdsDesplazados(@PathVariable Long id) {
        return turnoService.getIdsDesplazados(id);
    }
}
