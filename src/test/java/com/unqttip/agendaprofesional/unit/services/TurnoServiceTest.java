package com.unqttip.agendaprofesional.unit.services;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.PacienteDAO;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TurnoServiceTest {

    @Mock
    private TurnoDAO turnoDAO;
    @Mock
    private PacienteDAO pacienteDAO;
    @Mock
    private EntityManager entityManager;
    @InjectMocks
    private TurnoService turnoService;

    @Test
    void recuperarTurno_devuelveTurno() {
        LocalDateTime horaInicio = LocalDateTime.now().plusDays(1);
        LocalDateTime horaFin = LocalDateTime.now().plusDays(1).plusHours(1);
        Turno turno = Turno.builder()
                .id(1L)
                .horarioInicio(horaInicio)
                .horarioFin(horaFin)
                .tipo("Consulta")
                .paciente(crearPacienteTest())
                .build();

        when(turnoDAO.findById(1L)).thenReturn(Optional.of(turno));

        Turno turnoRes = turnoService.recuperarTurno(1L);

        verify(turnoDAO, atLeastOnce()).findById(1L);
        assertEquals(turno, turnoRes);
    }

    @Test
    void recuperarTurnoNoExistente_devuelveErrorNotFound() {
        when(turnoDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {turnoService.recuperarTurno(1L);});
    }

    @Test
    void recuperarTurnos_devuelveListaTurnos() {
        LocalDateTime horaInicio = LocalDateTime.now().plusDays(1);
        LocalDateTime horaFin = LocalDateTime.now().plusDays(1).plusHours(1);
        Turno turno = Turno.builder()
                .id(1L)
                .horarioInicio(horaInicio)
                .horarioFin(horaFin)
                .tipo("Consulta")
                .paciente(crearPacienteTest())
                .build();

        when(turnoDAO.findAll()).thenReturn(Collections.singletonList(turno));

        List<Turno> turnosRes = turnoService.recuperarTurnos();

        assertTrue(turnosRes.contains(turno));
    }

    @Test
    void guardarTurno_seGuardaCorrectamente() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo("Consulta")
                .fecha("2023-04-21")
                .horaInicio("09:00")
                .horaFin("09:30")
                .paciente(1L)
                .build();
        Paciente paciente = crearPacienteTest();

        Turno turnoEsperado = Turno.builder()
                .horarioInicio(LocalDateTime.of(2023, 4, 21, 9, 0))
                .horarioFin(LocalDateTime.of(2023, 4, 21, 9, 30))
                .tipo("Consulta")
                .paciente(paciente)
                .build();

        when(pacienteDAO.findById(1L)).thenReturn(Optional.of(paciente));
        when(entityManager.getReference(Paciente.class, 1L)).thenReturn(paciente);
        when(turnoDAO.findInTheSameHour(turnoEsperado.getHorarioInicio(), turnoEsperado.getHorarioFin())).thenReturn(0);

        turnoService.guardarTurno(nuevoTurnoDTO);
        verify(turnoDAO, atLeastOnce()).save(any(Turno.class));
        verify(turnoDAO, atLeastOnce()).findInTheSameHour(turnoEsperado.getHorarioInicio(), turnoEsperado.getHorarioFin());
        verify(pacienteDAO, atLeastOnce()).findById(1L);
        verify(entityManager, atLeastOnce()).getReference(Paciente.class, 1L);
    }

    @Test
    void guardarTurnoNull_devuelveBadRequest() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo(null)
                .fecha("2023-04-21")
                .horaInicio("09:00")
                .horaFin("09:30")
                .paciente(1L)
                .build();

        assertThrows(BadRequestException.class, () -> {turnoService.guardarTurno(nuevoTurnoDTO);});
        verifyNoInteractions(turnoDAO);
        verifyNoInteractions(pacienteDAO);
        verifyNoInteractions(entityManager);
    }

    @Test
    void guardarTurnoConPacienteInexistente_devuelveErrorNotFound() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo("Consulta")
                .fecha("2023-04-21")
                .horaInicio("09:00")
                .horaFin("09:30")
                .paciente(1L)
                .build();

        when(pacienteDAO.findById(1L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> {turnoService.guardarTurno(nuevoTurnoDTO);});
        verifyNoInteractions(turnoDAO);
        verify(pacienteDAO, atLeastOnce()).findById(1L);
        verifyNoInteractions(entityManager);
    }

    @Test
    void guardarTurnoConHorarioAlReves_devuelveErrorBadRequest() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo("Consulta")
                .fecha("2023-04-21")
                .horaInicio("09:30")
                .horaFin("09:00")
                .paciente(1L)
                .build();
        Paciente paciente = crearPacienteTest();

        Turno turnoEsperado = Turno.builder()
                .horarioInicio(LocalDateTime.of(2023, 4, 21, 9, 30))
                .horarioFin(LocalDateTime.of(2023, 4, 21, 9, 0))
                .tipo("Consulta")
                .paciente(paciente)
                .build();

        when(pacienteDAO.findById(1L)).thenReturn(Optional.of(paciente));
        when(entityManager.getReference(Paciente.class, 1L)).thenReturn(paciente);

        assertThrows(BadRequestException.class, () -> {turnoService.guardarTurno(nuevoTurnoDTO);});
        verifyNoInteractions(turnoDAO);
        verify(pacienteDAO, atLeastOnce()).findById(1L);
        verify(entityManager, atLeastOnce()).getReference(Paciente.class, 1L);
    }

    @Test
    void guardarTurnoEnHorarioOcupado_devuelveBadRequestException() {
        NuevoTurnoDTO nuevoTurnoDTO = NuevoTurnoDTO.builder()
                .tipo("Consulta")
                .fecha("2023-04-21")
                .horaInicio("09:00")
                .horaFin("09:30")
                .paciente(1L)
                .build();
        Paciente paciente = crearPacienteTest();

        Turno turnoEsperado = Turno.builder()
                .horarioInicio(LocalDateTime.of(2023, 4, 21, 9, 0))
                .horarioFin(LocalDateTime.of(2023, 4, 21, 9, 30))
                .tipo("Consulta")
                .paciente(paciente)
                .build();

        when(pacienteDAO.findById(1L)).thenReturn(Optional.of(paciente));
        when(entityManager.getReference(Paciente.class, 1L)).thenReturn(paciente);
        when(turnoDAO.findInTheSameHour(turnoEsperado.getHorarioInicio(), turnoEsperado.getHorarioFin())).thenReturn(1);

        assertThrows(BadRequestException.class, () -> {turnoService.guardarTurno(nuevoTurnoDTO);});
        verify(turnoDAO, atLeastOnce()).findInTheSameHour(turnoEsperado.getHorarioInicio(), turnoEsperado.getHorarioFin());
        verify(pacienteDAO, atLeastOnce()).findById(1L);
        verify(entityManager, atLeastOnce()).getReference(Paciente.class, 1L);
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
