package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreguntaDAO extends JpaRepository<Pregunta, Long> {
}
