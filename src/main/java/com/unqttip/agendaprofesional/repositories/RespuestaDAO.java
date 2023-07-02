package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RespuestaDAO extends JpaRepository<Respuesta, Long> {
}
