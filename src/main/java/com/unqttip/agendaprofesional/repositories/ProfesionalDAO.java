package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfesionalDAO extends JpaRepository<Profesional, String> {
}
