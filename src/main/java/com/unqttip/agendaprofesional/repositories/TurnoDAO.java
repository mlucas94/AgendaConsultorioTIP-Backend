package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.services.TurnoService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TurnoDAO extends JpaRepository<Turno, Long> {
    @Query(value = "select count(1) from turnos where estado = 0 and ((:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin))", nativeQuery = true)
    Integer findCountInTheSameHour(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select * from turnos where estado = 0 and ((horario_inicio between :hora_inicio and :hora_fin) or (horario_fin between :hora_inicio and :hora_fin)) order by horario_inicio asc;", nativeQuery = true)
    List<Turno> findWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select count(1) from turnos where tipo in (1,2) and estado = 0 and ((:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin));", nativeQuery = true)
    Integer findCountNonRegularsWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select count(1) from turnos where tipo = 2 and estado = 0 and ((:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin));", nativeQuery = true)
    Integer findCountPrioritariosWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value ="select * from turnos where estado = 0 and DATE(horario_inicio) = :fecha order by horario_inicio asc", nativeQuery = true)
    List<Turno> findAllByHorarioInicio(@Param("fecha") String fecha);

    @Query(value="select count(id) from turnos where estado = 0 and DATE(horario_inicio) =:fecha", nativeQuery = true)
    Integer countTurnosDelDia(@Param("fecha") String fecha);

    @Query(value="select count(id) from turnos where estado = 0 and DATE(horario_inicio) =:fecha and tipo in (2)", nativeQuery = true)
    Integer countTurnosPrioritariosDelDia(@Param("fecha") String fecha);

    @Query(value="select * from turnos where estado = 0 and month(horario_inicio) = month(:fecha) and tipo in (2)", nativeQuery = true)
    List<Turno> selectHoraInicioWhereMonth(@Param("fecha") String fecha);

    @Query(value="select * from turnos where estado = 0 and horario_inicio > current_date AND paciente_id = :idPaciente  ORDER BY horario_inicio ASC LIMIT 1", nativeQuery = true)
    Turno proximoTurnoPaciente(@Param("idPaciente") Long id);

    @Query(value="select count(id) from turnos where estado = 0 and DATE(horario_inicio) = CURRENT_DATE", nativeQuery = true)
    Integer countTurnosHoy();

    @Query(value="select * from turnos where estado = 0 and horario_inicio > NOW() order by horario_inicio ASC LIMIT 10", nativeQuery = true)
    List<Turno> findProximosTurnos();

    @Query(value="select * from turnos where estado = 0 and horario_inicio > current_date AND tipo in (2)  ORDER BY horario_inicio ASC LIMIT 1", nativeQuery = true)
    Optional<Turno> proximoPrioritario();
}
