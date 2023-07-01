package com.unqttip.agendaprofesional.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "formularios")
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    @OneToMany(fetch = FetchType.LAZY)
    private List<Pregunta> preguntas;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Pregunta> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<Pregunta> preguntas) {
        this.preguntas = preguntas;
    }

    public static FormularioBuilder builder() {
        return new FormularioBuilder();
    }

    public static final class FormularioBuilder {
        private Formulario formulario;

        private FormularioBuilder() {
            formulario = new Formulario();
        }

        public FormularioBuilder id(Long id) {
            formulario.setId(id);
            return this;
        }

        public FormularioBuilder titulo(String titulo) {
            formulario.setTitulo(titulo);
            return this;
        }

        public FormularioBuilder preguntas(List<Pregunta> preguntas) {
            formulario.setPreguntas(preguntas);
            return this;
        }

        public Formulario build() {
            return formulario;
        }
    }
}
