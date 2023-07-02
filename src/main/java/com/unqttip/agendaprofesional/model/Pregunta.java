package com.unqttip.agendaprofesional.model;

import javax.persistence.*;

@Entity
@Table(name = "preguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String preguntaNombre;
    private Boolean obligatoria;
    private TipoDeRespuesta tipo;
    private String opciones;
    @ManyToOne(fetch = FetchType.LAZY)
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

    public TipoDeRespuesta getTipo() {
        return tipo;
    }

    public void setTipo(TipoDeRespuesta tipo) {
        this.tipo = tipo;
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

        public PreguntaBuilder preguntaNombre(String preguntaNombre) {
            pregunta.setPreguntaNombre(preguntaNombre);
            return this;
        }

        public PreguntaBuilder obligatoria(Boolean obligatoria) {
            pregunta.setObligatoria(obligatoria);
            return this;
        }

        public PreguntaBuilder tipo(TipoDeRespuesta tipo) {
            pregunta.setTipo(tipo);
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
