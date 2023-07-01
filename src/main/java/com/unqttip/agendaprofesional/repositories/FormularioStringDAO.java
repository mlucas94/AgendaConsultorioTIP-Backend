package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.FormularioString;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioStringDAO extends JpaRepository<FormularioString, Long> {
}
