package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;
import com.unqttip.agendaprofesional.model.RangoDeTurno;
import com.unqttip.agendaprofesional.exceptions.BadRequestException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.TipoDeTurno;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.PacienteDAO;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurnoService {

    @Autowired
    private TurnoDAO turnoDAO;
    @Autowired
    private PacienteDAO pacienteDAO;
    @Autowired
    private EntityManager entityManager;

    public Turno recuperarTurno(Long id) {
        Optional<Turno> maybeTurno = turnoDAO.findById(id);
        if (maybeTurno.isEmpty()) {
            throw new NotFoundException("El turno " + id + " no existe.");
        }
        return maybeTurno.get();
    }

    public List<Turno> recuperarTurnos() {
        return turnoDAO.findAll();
    }

    public void guardarTurno(NuevoTurnoDTO turnoDto) {
        if (turnoDto == null || turnoDto.hasNullProperties()) {
            throw new BadRequestException("Es necesario contar con todas las propiedades del turno para crearlo.");
        }
        if (pacienteDAO.findById(turnoDto.getPaciente()).isEmpty()) {
            throw new NotFoundException("El usuario " + turnoDto.getPaciente() + " no existe.");
        }
        Turno nuevoTurno = turnoDto.turnoFromDTO(entityManager);
        validarTurno(nuevoTurno);
        turnoDAO.save(nuevoTurno);
    }

    private void validarTurno(Turno turno) {
        if (turno.getHorarioFin().isBefore(turno.getHorarioInicio())) {
            throw new BadRequestException("Un turno no puede tener una hora final previa a la hora de inicio.");
        }
        if (turno.getTipo() == TipoDeTurno.REGULAR && turnoDAO.findCountInTheSameHour(turno.getHorarioInicio().plusMinutes(1), turno.getHorarioFin().minusMinutes(1)) > 0) {
            throw new BadRequestException("Existen turnos guardados en el horario elegido.");
        }
        if (turno.getTipo() == TipoDeTurno.SOBRETURNO && turnoDAO.findCountNonRegularsWithinHourRange(turno.getHorarioInicio().plusMinutes(1), turno.getHorarioFin().minusMinutes(1)) > 0) {
            throw new BadRequestException("Existen turnos guardados en el horario elegido.");
        }
        if (turno.getTipo() == TipoDeTurno.PRIORITARIO && turnoDAO.findCountPrioritariosWithinHourRange(turno.getHorarioInicio().plusMinutes(1), turno.getHorarioFin().minusMinutes(1)) > 0) {
            throw new BadRequestException("Existen turnos guardados en el horario elegido.");
        }
    }

    public List<RangoDeTurnoDTO> recuperarBandasHorariasDisponibles(LocalDate fecha, TipoDeTurno tipoDeTurno) {
        LocalDateTime horaInicio = LocalDateTime.of(fecha, LocalTime.MIN);
        LocalDateTime horaFin = LocalDateTime.of(fecha, LocalTime.MAX);

        if (tipoDeTurno == TipoDeTurno.PRIORITARIO) {
            return deListaDeModeloAListaDeDTO(dividirEnRangosDeTiempo(horaInicio, horaFin));
        }

        LocalDateTime horaLaboralInicio = LocalDateTime.of(fecha, LocalTime.of(9, 0, 0));
        LocalDateTime horaLaboralFin = LocalDateTime.of(fecha, LocalTime.of(18, 0, 0));

        List<RangoDeTurno> rangoDeTurnosDisponibles = dividirEnRangosDeTiempo(horaLaboralInicio, horaLaboralFin);

        List<Turno> turnosDelDia = turnoDAO.findWithinHourRange(horaInicio, horaFin);
        if (tipoDeTurno == TipoDeTurno.SOBRETURNO) {
            turnosDelDia = turnosDelDia.stream().filter(turno -> turno.getTipo() != TipoDeTurno.REGULAR).collect(Collectors.toList());
            return deListaDeModeloAListaDeDTO(chequearDisponibilidad(rangoDeTurnosDisponibles, turnosDelDia));
        }

        if (turnosDelDia.isEmpty()) {
            return deListaDeModeloAListaDeDTO(rangoDeTurnosDisponibles);
        }

        return deListaDeModeloAListaDeDTO(chequearDisponibilidad(rangoDeTurnosDisponibles, turnosDelDia));
    }

    private List<RangoDeTurno> chequearDisponibilidad(List<RangoDeTurno> rangoDeTurnos, List<Turno> turnos) {
        for (RangoDeTurno rangoDeTurno : rangoDeTurnos) {
            for (Turno turno : turnos) {
                if (!rangoDeTurno.sigueDisponible(turno)) {
                    rangoDeTurno.setDisponible(false);
                    break;
                }
            }
        }
        return rangoDeTurnos;
    }

    private List<RangoDeTurno> dividirEnRangosDeTiempo(LocalDateTime inicio, LocalDateTime fin) {
        List<RangoDeTurno> rangosDeTiempo = new ArrayList<>();

        LocalDateTime tiempoActual = inicio;
        while (tiempoActual.isBefore(fin)) {
            LocalDateTime proxima = tiempoActual.plusMinutes(30);
            if (proxima.isAfter(fin)) {
                proxima = fin;
            }
            rangosDeTiempo.add(new RangoDeTurno(tiempoActual, proxima, true));
            tiempoActual = proxima;
        }

        return rangosDeTiempo;
    }

    private List<RangoDeTurnoDTO> deListaDeModeloAListaDeDTO(List<RangoDeTurno> rangoDeTurnos) {
        return rangoDeTurnos.stream().map(RangoDeTurno::fromModelObject).collect(Collectors.toList());
    }
}
