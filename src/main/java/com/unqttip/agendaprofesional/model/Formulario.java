package com.unqttip.agendaprofesional.model;

import com.unqttip.agendaprofesional.dtos.FormularioCompletableDTO;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "formularios")
public class Formulario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public FormularioCompletableDTO fromModelToDTO() {
        return FormularioCompletableDTO.builder()
                .id(this.id)
                .titulo(this.titulo)
                .preguntas(this.preguntas.stream().map(Pregunta::fromModelToDTO).collect(Collectors.toList()))
                .build();
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
