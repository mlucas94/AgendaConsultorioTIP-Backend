package com.unqttip.agendaprofesional.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "formulario")
    private Set<Pregunta> preguntas;
}
