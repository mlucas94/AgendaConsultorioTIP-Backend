package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface TurnoDAO extends JpaRepository<Turno, Long> {
    @Query(value = "select count(1) from turnos where (:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin)", nativeQuery = true)
    Integer findCountInTheSameHour(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select * from turnos where (horario_inicio between :hora_inicio and :hora_fin) or (horario_fin between :hora_inicio and :hora_fin) order by horario_inicio asc;", nativeQuery = true)
    List<Turno> findWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select count(1) from turnos where tipo in (1,2) and ((:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin));", nativeQuery = true)
    Integer findCountNonRegularsWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value = "select count(1) from turnos where tipo = 2 and ((:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin));", nativeQuery = true)
    Integer findCountPrioritariosWithinHourRange(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);

    @Query(value ="select * from turnos where DATE(horario_inicio) = :fecha order by horario_inicio asc", nativeQuery = true)
    List<Turno> findAllByHorarioInicio(@Param("fecha") String fecha);

    @Query(value="select count(id) from turnos where DATE(horario_inicio) =:fecha", nativeQuery = true)
    Integer countTurnosDelDia(@Param("fecha") String fecha);

    @Query(value="select count(id) from turnos where DATE(horario_inicio) =:fecha and tipo in (2)", nativeQuery = true)
    Integer countTurnosPrioritariosDelDia(@Param("fecha") String fecha);

    @Query(value="select * from turnos where month(horario_inicio) = month(:fecha) and tipo in (2)", nativeQuery = true)
    List<Turno> selectHoraInicioWhereMonth(@Param("fecha") String fecha);

}
