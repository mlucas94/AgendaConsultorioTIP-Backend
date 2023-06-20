package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Archivo;
import com.unqttip.agendaprofesional.model.Turno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArchivoDAO extends JpaRepository<Archivo, Long>, PagingAndSortingRepository<Archivo, Long> {

    Page<Archivo> findByPacienteId(Long pacienteId, Pageable pageable);

    Page<Archivo> findByTurnos(Turno turno, Pageable pageable);
}
