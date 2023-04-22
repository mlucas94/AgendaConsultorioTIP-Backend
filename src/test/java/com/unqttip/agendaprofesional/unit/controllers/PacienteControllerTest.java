package com.unqttip.agendaprofesional.unit.controllers;

import com.unqttip.agendaprofesional.controllers.PacienteController;
import com.unqttip.agendaprofesional.dtos.NuevoPacienteDTO;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.services.PacienteService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PacienteControllerTest {

    @Mock
    private PacienteService pacienteService;
    @InjectMocks
    private PacienteController pacienteController;

    @Test
    void recuperarPaciente_devuelvePaciente() {
        Paciente pacienteEsperado = crearPacienteTest();

        when(pacienteService.recuperarPaciente(1L)).thenReturn(pacienteEsperado);

        ResponseEntity<Paciente> pacienteRes = pacienteController.recuperarPaciente(1L);
        assertEquals(HttpStatus.OK, pacienteRes.getStatusCode());
        assertEquals(pacienteEsperado, pacienteRes.getBody());
        verify(pacienteService, atLeastOnce()).recuperarPaciente(1L);
    }

    @Test
    void recuperarPacientePorDni_devuelvePaciente() {
        Paciente pacienteEsperado = crearPacienteTest();

        when(pacienteService.recuperarPacientePorDni(123L)).thenReturn(pacienteEsperado);

        ResponseEntity<Paciente> pacienteRes = pacienteController.recuperarPacientePorDni(123L);
        assertEquals(HttpStatus.OK, pacienteRes.getStatusCode());
        assertEquals(pacienteEsperado, pacienteRes.getBody());
        verify(pacienteService, atLeastOnce()).recuperarPacientePorDni(123L);
    }

    @Test
    void recuperarPacientePorDniSimilar_devuelveListaDePaciente() {
        List<Paciente> listaPacientesEsperados = Collections.singletonList(crearPacienteTest());

        when(pacienteService.recuperarPacientesPorDniSimilar(12L)).thenReturn(listaPacientesEsperados);

        ResponseEntity<List<Paciente>> listaPacientesRes = pacienteController.recuperarPacientesPorDniSimilar(12L);
        assertEquals(HttpStatus.OK, listaPacientesRes.getStatusCode());
        assertEquals(listaPacientesEsperados, listaPacientesRes.getBody());
        verify(pacienteService, atLeastOnce()).recuperarPacientesPorDniSimilar(12L);
    }

    @Test
    void guardarPaciente_guardaPaciente() {
        NuevoPacienteDTO nuevoPacienteDTO = NuevoPacienteDTO.builder()
                .dni(123L)
                .nombre("Juan Perez")
                .email(null)
                .telefono(1158888888)
                .edad(30)
                .obraSocial("Osde")
                .plan("210")
                .build();

        pacienteController.guardarPaciente(nuevoPacienteDTO);

        verify(pacienteService, atLeastOnce()).guardarPaciente(nuevoPacienteDTO);
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
