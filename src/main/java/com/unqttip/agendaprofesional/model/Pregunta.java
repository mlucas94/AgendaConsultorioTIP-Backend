package com.unqttip.agendaprofesional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unqttip.agendaprofesional.dtos.PreguntaCompletableDTO;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;

@Entity
@Table(name = "preguntas")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String preguntaNombre;
    private Boolean obligatoria;
    private TipoDeRespuesta tipoDeRespuesta;
    private String opciones;
    @ManyToOne
    @JsonIgnore
    private Formulario formulario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPreguntaNombre() {
        return preguntaNombre;
    }

    public void setPreguntaNombre(String preguntaNombre) {
        this.preguntaNombre = preguntaNombre;
    }

    public Boolean getObligatoria() {
        return obligatoria;
    }

    public void setObligatoria(Boolean obligatoria) {
        this.obligatoria = obligatoria;
    }

    public TipoDeRespuesta getTipoDeRespuesta() {
        return tipoDeRespuesta;
    }

    public void setTipoDeRespuesta(TipoDeRespuesta tipoDeRespuesta) {
        this.tipoDeRespuesta = tipoDeRespuesta;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public PreguntaCompletableDTO fromModelToDTO() {
        return PreguntaCompletableDTO.builder()
                .id(this.id)
                .pregunta_nombre(this.preguntaNombre)
                .obligatoria(this.obligatoria)
                .tipo(this.tipoDeRespuesta.toString())
                .opciones(new ArrayList<>(Arrays.asList(this.opciones.split(";"))))
                .build();
    }

    public static PreguntaBuilder builder() {
        return new PreguntaBuilder();
    }

    public static final class PreguntaBuilder {
        private Pregunta pregunta;

        private PreguntaBuilder() {
            pregunta = new Pregunta();
        }

        public PreguntaBuilder id(Long id) {
            pregunta.setId(id);
            return this;
        }

        public PreguntaBuilder preguntaNombre(String preguntaNombre) {
            pregunta.setPreguntaNombre(preguntaNombre);
            return this;
        }

        public PreguntaBuilder obligatoria(Boolean obligatoria) {
            pregunta.setObligatoria(obligatoria);
            return this;
        }

        public PreguntaBuilder tipoDeRespuesta(TipoDeRespuesta tipoDeRespuesta) {
            pregunta.setTipoDeRespuesta(tipoDeRespuesta);
            return this;
        }

        public PreguntaBuilder opciones(String opciones) {
            pregunta.setOpciones(opciones);
            return this;
        }

        public PreguntaBuilder formulario(Formulario formulario) {
            pregunta.setFormulario(formulario);
            return this;
        }

        public Pregunta build() {
            return pregunta;
        }
    }

    public Boolean tieneOpciones() {
        return !(this.opciones == null) && !(this.opciones.equals(""));
    }
}
