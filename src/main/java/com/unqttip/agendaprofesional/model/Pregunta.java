package com.unqttip.agendaprofesional.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pregunta_nombre;
    private Boolean obligatoria;
    private TipoDeRespuesta tipoDeRespuesta;
    private String opciones; // opciones separadas por valor especifico, se guarda como string entero
}
