package com.unqttip.agendaprofesional.unit.controllers;

import com.unqttip.agendaprofesional.controllers.TurnoController;
import com.unqttip.agendaprofesional.dtos.ConsultaTurnosDisponiblesDTO;
import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.model.TipoDeTurno;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

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

    /*@Test
    void recuperarTurnos_devuelveListaTurnos() {
        List<Turno> listaTurnoEsperada = Collections.singletonList(crearTurnoTest());

        when(turnoService.recuperarTurnos()).thenReturn(listaTurnoEsperada);

        ResponseEntity<List<Turno>> listaTurnoRes = turnoController.recuperarTurnos();
        assertEquals(HttpStatus.OK, listaTurnoRes.getStatusCode());
        assertEquals(listaTurnoEsperada, listaTurnoRes.getBody());
        verify(turnoService, atLeastOnce()).recuperarTurnos();
    }*/

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

    @Test
    void recuperarHorariosDisponiblesTest() {
        String fechaConsultadaParam = "2023-06-04";
        String tipoDeTurnoParam = "REGULAR";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaConsulta = LocalDate.parse(fechaConsultadaParam, formatter);
        TipoDeTurno tipoDeTurnoConsulta = TipoDeTurno.valueOf(tipoDeTurnoParam);

        List<RangoDeTurnoDTO> rangoDeTurnoDTOListEsperada = Collections.singletonList(new RangoDeTurnoDTO());
        when(turnoService.recuperarBandasHorariasDisponibles(fechaConsulta, tipoDeTurnoConsulta)).thenReturn(rangoDeTurnoDTOListEsperada);

        ResponseEntity<List<RangoDeTurnoDTO>> rangoDeTurnoDTOListRes = turnoController.recuperarHorariosDisponibles(fechaConsultadaParam, tipoDeTurnoParam);

        assertEquals(HttpStatus.OK, rangoDeTurnoDTOListRes.getStatusCode());
        assertEquals(rangoDeTurnoDTOListEsperada, rangoDeTurnoDTOListRes.getBody());
        verify(turnoService, atLeastOnce()).recuperarBandasHorariasDisponibles(fechaConsulta, tipoDeTurnoConsulta);
    }

    private Turno crearTurnoTest() {
        return Turno.builder()
                .id(1L)
                .horarioInicio(LocalDateTime.now().plusDays(1L))
                .horarioFin(LocalDateTime.now().plusDays(1L).plusMinutes(30L))
                .tipo(TipoDeTurno.REGULAR)
                .paciente(crearPacienteTest())
                .build();
    }

    private Paciente crearPacienteTest() {
        return Paciente.builder()
                .id(1L)
                .dni(123L)
                .nombre("Juan Perez")
                .email("juanperez@gmail.com")
                .telefono(1158888888L)
                .edad(40)
                .obraSocial("Osde")
                .plan("210")
                .turnos(Collections.emptyList())
                .build();
    }
}
