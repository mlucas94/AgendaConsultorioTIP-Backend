package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Profesional;
import com.unqttip.agendaprofesional.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaDAO extends JpaRepository<Respuesta, Long> {
    @Query("select * from respuestas where paciente_id = :idPaciente")
    List<Respuesta> findByPacienteId(@Param("idPaciente") Long idPaciente);
}
