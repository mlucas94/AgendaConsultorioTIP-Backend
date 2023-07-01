package com.unqttip.agendaprofesional.model;

import javax.persistence.*;

@Entity
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pregunta_nombre;
    private Boolean obligatoria;
    private TipoDeRespuesta tipoDeRespuesta;
    private String opciones;
    @ManyToOne
    private Formulario formulario;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPregunta_nombre() {
        return pregunta_nombre;
    }

    public void setPregunta_nombre(String pregunta_nombre) {
        this.pregunta_nombre = pregunta_nombre;
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

        public PreguntaBuilder pregunta_nombre(String pregunta_nombre) {
            pregunta.setPregunta_nombre(pregunta_nombre);
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
