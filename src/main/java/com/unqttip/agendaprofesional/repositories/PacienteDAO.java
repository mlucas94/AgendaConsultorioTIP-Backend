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

    @Query(value = "select * from pacientes p where dni like %:dniONombre% or nombre like %:dniONombre% order by dni limit 5", nativeQuery = true)
    List<Paciente> findByDniLikeOrNombreLikeOrderedByDni(@Param("dniONombre")String dniONombre);
}
