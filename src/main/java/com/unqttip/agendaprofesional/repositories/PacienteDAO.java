package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PacienteDAO extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findByDni(Long dni);

    @Query(value = "from pacientes p where dni like %:dni% order by dni", nativeQuery = true)
    List<Paciente> findByDniLikeOrderedByDni(@Param("dni")Long dni);
}
