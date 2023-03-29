package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurnoDAO extends JpaRepository<Turno, Long> {
}
