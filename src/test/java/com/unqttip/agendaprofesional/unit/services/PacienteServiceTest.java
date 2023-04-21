package com.unqttip.agendaprofesional.unit.services;

import com.unqttip.agendaprofesional.dtos.NuevoPacienteDTO;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.repositories.PacienteDAO;
import com.unqttip.agendaprofesional.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteServiceTest {

    @Mock
    private PacienteDAO pacienteDAO;
    @InjectMocks
    private PacienteService pacienteService;

    @Test
    void recuperarPaciente_devuelvePaciente() {
        // arrange
        Paciente paciente = Paciente.builder()
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

        when(pacienteDAO.findById(1L)).thenReturn(Optional.of(paciente));

        // act
        Paciente pacienteRes = pacienteService.recuperarPaciente(1L);

        // assert
        verify(pacienteDAO, atLeastOnce()).findById(1L);
        assertEquals(paciente, pacienteRes);
    }

    @Test
    void recuperarPacienteNoExistente_devuelveErrorNotFound() {
        // arrange
        when(pacienteDAO.findById(1L)).thenReturn(Optional.empty());

        // act - assert
        assertThrows(NotFoundException.class, () -> {pacienteService.recuperarPaciente(1L);});
    }

    @Test
    void recuperarPacientePorDni_devuelvePaciente() {
        // arrange
        Paciente paciente = Paciente.builder()
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

        when(pacienteDAO.findByDni(123L)).thenReturn(Optional.of(paciente));

        // act
        Paciente pacienteRes = pacienteService.recuperarPacientePorDni(123L);

        // assert
        verify(pacienteDAO, atLeastOnce()).findByDni(123L);
        assertEquals(paciente, pacienteRes);
    }

    @Test
    void recuperarPacientePorDniNoExistente_devuelveErrorNotFound() {
        // arrange
        when(pacienteDAO.findByDni(123L)).thenReturn(Optional.empty());

        // act - assert
        assertThrows(NotFoundException.class, () -> {pacienteService.recuperarPacientePorDni(123L);});
    }

    @Test
    void recuperarPacientePorDniSimilar_devuelveListaPaciente() {
        //arrange
        Paciente paciente1 = Paciente.builder()
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

        when(pacienteDAO.findByDniLikeOrderedByDni(1L)).thenReturn(Collections.singletonList(paciente1));

        // act
        List<Paciente> pacienteListRes = pacienteService.recuperarPacientesPorDniSimilar(1L);

        // assert
        verify(pacienteDAO, atLeastOnce()).findByDniLikeOrderedByDni(1L);
        assertEquals(1, pacienteListRes.size());
        assertEquals(paciente1, pacienteListRes.get(0));
    }

    @Test
    void recuperarPacientePorDniSimilar_devuelveListaVacia() {
        //arrange
        when(pacienteDAO.findByDniLikeOrderedByDni(1L)).thenReturn(Collections.emptyList());

        // act
        List<Paciente> pacienteListRes = pacienteService.recuperarPacientesPorDniSimilar(1L);

        // assert
        verify(pacienteDAO, atLeastOnce()).findByDniLikeOrderedByDni(1L);
        assertTrue(pacienteListRes.isEmpty());
    }

    @Test
    void guardarPaciente_seGuardaEnBase() {
        NuevoPacienteDTO nuevoPacienteDTO = NuevoPacienteDTO.builder()
                .dni(123L)
                .nombre("Juan Perez")
                .email("juanperez@gmail.com")
                .telefono(1158888888)
                .edad(30)
                .obraSocial("Osde")
                .plan("210")
                .build();

        pacienteService.guardarPaciente(nuevoPacienteDTO);
        verify(pacienteDAO, atLeastOnce()).save(any(Paciente.class));
    }

    @Test
    void guardarPacienteIncompleto_devuelveErrorBadRequest() {
        NuevoPacienteDTO nuevoPacienteDTO = NuevoPacienteDTO.builder()
                .dni(123L)
                .nombre("Juan Perez")
                .email(null)
                .telefono(1158888888)
                .edad(30)
                .obraSocial("Osde")
                .plan("210")
                .build();

        assertThrows(BadRequestException.class, () -> {pacienteService.guardarPaciente(nuevoPacienteDTO);});
    }

    @Test
    void guardarPacienteNull_devuelveErrorBadRequest() {
        assertThrows(BadRequestException.class, () -> {pacienteService.guardarPaciente(null);});
    }
}
