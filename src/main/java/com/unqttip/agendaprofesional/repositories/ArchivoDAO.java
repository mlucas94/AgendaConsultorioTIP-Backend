package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Archivo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchivoDAO extends JpaRepository<Archivo, Long> {
}
