package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfesionalDAO extends JpaRepository<Profesional, Long> {
    Optional<Profesional> findByEmail(String email);
}
