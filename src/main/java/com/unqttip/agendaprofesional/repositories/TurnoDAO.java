package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TurnoDAO extends JpaRepository<Turno, Long> {
    @Query(value = "select count(1) from turnos where (:hora_inicio between horario_inicio and horario_fin) or (:hora_fin between horario_inicio and horario_fin)", nativeQuery = true)
    Integer findInTheSameHour(@Param("hora_inicio") LocalDateTime hora_inicio, @Param("hora_fin") LocalDateTime hora_fin);
}
