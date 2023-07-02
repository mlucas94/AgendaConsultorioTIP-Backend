package com.unqttip.agendaprofesional.repositories;

import com.unqttip.agendaprofesional.model.Formulario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormularioDAO extends JpaRepository<Formulario, Long> {
}
