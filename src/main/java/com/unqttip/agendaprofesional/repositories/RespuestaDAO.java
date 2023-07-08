package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Respuesta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RespuestaDAO extends JpaRepository<Respuesta, Long> {

    @Query(value ="select * from respuestas where paciente_id = :idPaciente", nativeQuery = true)
    List<Respuesta> findByPaciente(@Param("idPaciente") Long idPaciente);

    @Query(value ="select * from respuestas where paciente_id = :idPaciente and formulario_id = :idFormulario", nativeQuery = true)
    List<Respuesta> findByPacienteAndFormulario(@Param("idPaciente") Long idPaciente, @Param("idFormulario") Long idFormulario);

    @Query(value="select * from respuestas where turno_id = :idTurno", nativeQuery = true)
    List<Respuesta> findByTurno(@Param("idTurno") Long idTurno);

    @Query(value ="select * from respuestas where turno_id = :idTurno and formulario_id = :idFormulario", nativeQuery = true)
    List<Respuesta> findByTurnoAndFormulario(@Param("idTurno") Long idTurno, @Param("idFormulario") Long idFormulario);
}
