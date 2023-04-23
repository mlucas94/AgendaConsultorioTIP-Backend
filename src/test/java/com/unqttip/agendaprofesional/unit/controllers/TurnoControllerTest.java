package com.unqttip.agendaprofesional.unit.controllers;

import com.unqttip.agendaprofesional.controllers.TurnoController;
import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TurnoControllerTest {

    @Mock
    private TurnoService turnoService;
    @InjectMocks
    private TurnoController turnoController;

    @Test
    void recuperarTurno_devuelveTurno() {
        Turno turnoEsperado = crearTurnoTest();

        when(turnoService.recuperarTurno(1L)).thenReturn(turnoEsperado);

        ResponseEntity<Turno> turnoRes = turnoController.recuperarTurno(1L);
        assertEquals(HttpStatus.OK, turnoRes.getStatusCode());
        assertEquals(turnoEsperado, turnoRes.getBody());
        verify(turnoService, atLeastOnce()).recuperarTurno(1L);
    }

    @Test
    void recuperarTurnos_devuelveListaTurnos() {
        List<Turno> listaTurnoEsperada = Collections.singletonList(crearTurnoTest());

        when(turnoService.recuperarTurnos()).thenReturn(listaTurnoEsperada);

        ResponseEntity<List<Turno>> listaTurnoRes = turnoController.recuperarTurnos();
        assertEquals(HttpStatus.OK, listaTurnoRes.getStatusCode());
        assertEquals(listaTurnoEsperada, listaTurnoRes.getBody());
        verify(turnoService, atLeastOnce()).recuperarTurnos();
    }

    @Test
    void crearTurno_creaNuevoTurno() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo("Consulta")
                .fecha("2023-03-03")
                .horaInicio("09:00")
                .horaFin("10:00")
                .paciente(1L)
                .build();

        turnoController.crearTurno(nuevoTurnoDTO);

        verify(turnoService, atLeastOnce()).guardarTurno(nuevoTurnoDTO);
    }

    private Turno crearTurnoTest() {
        return Turno.builder()
                .id(1L)
                .horarioInicio(LocalDateTime.now().plusDays(1L))
                .horarioFin(LocalDateTime.now().plusDays(1L).plusMinutes(30L))
                .tipo("Consulta")
                .paciente(crearPacienteTest())
                .build();
    }

    private Paciente crearPacienteTest() {
        return Paciente.builder()
                .id(1L)
                .dni(123L)
                .nombre("Juan Perez")
                .email("juanperez@gmail.com")
                .telefono(1158888888)
                .edad(40)
                .obraSocial("Osde")
                .plan("210")
                .turnos(Collections.emptyList())
                .build();
    }
}