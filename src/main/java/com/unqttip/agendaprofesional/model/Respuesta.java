package com.unqttip.agendaprofesional.model;

import javax.persistence.*;

@Entity
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respuesta;
    @ManyToOne
    private Pregunta pregunta;
    @ManyToOne
    private Formulario formulario;
    @ManyToOne
    private Paciente paciente;
}