package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProfesionalDAO extends JpaRepository<Profesional, Long> {
    Optional<Profesional> findByEmail(String email);

    @Query(value = "select email from profesionales order by email asc", nativeQuery = true)
    List<String> findAllEmails();
}
