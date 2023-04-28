package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.NuevoTurnoDTO;
import com.unqttip.agendaprofesional.dtos.RangoDeTurnoDTO;
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
import java.util.stream.Stream;

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
        } else if (turnoDAO.findCountInTheSameHour(turno.getHorarioInicio(), turno.getHorarioFin()) > 0) {
            throw new BadRequestException("Existen turnos guardados en el horario elegido.");
        }
    }

    public List<RangoDeTurnoDTO> recuperarBandasHorariasDisponibles(LocalDate fecha, TipoDeTurno tipoDeTurno) {
        LocalDateTime horaInicio = LocalDateTime.of(fecha, LocalTime.MIN);
        LocalDateTime horaFin = LocalDateTime.of(fecha, LocalTime.MAX);

        if (tipoDeTurno == TipoDeTurno.PRIORITARIO) {
            return dividirEnRangosDeTiempo(horaInicio, horaFin);
        }

        LocalDateTime horaLaboralInicio = LocalDateTime.of(fecha, LocalTime.of(9, 0, 0));
        LocalDateTime horaLaboralFin = LocalDateTime.of(fecha, LocalTime.of(18, 0, 0));

        if (tipoDeTurno == TipoDeTurno.SOBRETURNO) {
            return dividirEnRangosDeTiempo(horaLaboralInicio, horaLaboralFin);
        }

        List<Turno> turnosDelDia = turnoDAO.findWithinHourRange(horaInicio, horaFin);
        List<RangoDeTurnoDTO> rangosDisponibles = recuperarHorariosDisponibles(horaLaboralInicio, horaLaboralFin, turnosDelDia);

        List<RangoDeTurnoDTO> turnosDivididos = new ArrayList<>();
        rangosDisponibles.forEach(rangoDeTurnoDTO -> {
            rangosDisponibles.addAll(dividirEnRangosDeTiempo(rangoDeTurnoDTO.getHoraInicio(), rangoDeTurnoDTO.getHoraFin()));
        });

        return turnosDivididos;
    }

    private List<RangoDeTurnoDTO> recuperarHorariosDisponibles(LocalDateTime horaInicio, LocalDateTime horaFin, List<Turno> turnos) {
        List<RangoDeTurnoDTO> rangoDeTurnoDTOList = new ArrayList<>();

        for (int i = 0; i < turnos.size(); i++) {
            if (i == 0) {
                rangoDeTurnoDTOList.add(new RangoDeTurnoDTO(horaInicio, turnos.get(i).getHorarioInicio()));
            } else if (i == turnos.size() - 1) {
                rangoDeTurnoDTOList.add(new RangoDeTurnoDTO(turnos.get(i-1).getHorarioFin(), turnos.get(i).getHorarioInicio()));
            } else {
                rangoDeTurnoDTOList.add(new RangoDeTurnoDTO(turnos.get(i-1).getHorarioFin(), horaFin));
            }
        }

        return rangoDeTurnoDTOList;
    }

    private List<RangoDeTurnoDTO> dividirEnRangosDeTiempo(LocalDateTime inicio, LocalDateTime fin) {
        List<RangoDeTurnoDTO> rangosDeTiempo = new ArrayList<>();

        LocalDateTime tiempoActual = inicio;
        while (tiempoActual.isBefore(inicio)) {
            LocalDateTime proxima = tiempoActual.plusMinutes(30);
            if (proxima.isAfter(fin)) {
                proxima = fin;
            }
            rangosDeTiempo.add(new RangoDeTurnoDTO(tiempoActual, proxima));
            tiempoActual = proxima;
        }

        return rangosDeTiempo;
    }
}
