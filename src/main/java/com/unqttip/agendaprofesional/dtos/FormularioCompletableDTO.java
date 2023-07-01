package com.unqttip.agendaprofesional.dtos;

import java.util.List;

public class FormularioCompletableDTO {
    private Long id;
    private String titulo;
    private List<PreguntaCompletableDTO> preguntas;

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

    public List<PreguntaCompletableDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<PreguntaCompletableDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public static FormularioCompletableDTOBuilder builder() {
        return new FormularioCompletableDTOBuilder();
    }

    public static final class FormularioCompletableDTOBuilder {
        private FormularioCompletableDTO formularioCompletableDTO;

        private FormularioCompletableDTOBuilder() {
            formularioCompletableDTO = new FormularioCompletableDTO();
        }

        public FormularioCompletableDTOBuilder id(Long id) {
            formularioCompletableDTO.setId(id);
            return this;
        }

        public FormularioCompletableDTOBuilder titulo(String titulo) {
            formularioCompletableDTO.setTitulo(titulo);
            return this;
        }

        public FormularioCompletableDTOBuilder preguntas(List<PreguntaCompletableDTO> preguntas) {
            formularioCompletableDTO.setPreguntas(preguntas);
            return this;
        }

        public FormularioCompletableDTO build() {
            return formularioCompletableDTO;
        }
    }
}
