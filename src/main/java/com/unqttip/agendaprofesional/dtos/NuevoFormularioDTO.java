package com.unqttip.agendaprofesional.dtos;

import com.unqttip.agendaprofesional.model.Formulario;

import java.util.List;
import java.util.stream.Collectors;

public class NuevoFormularioDTO {
    private String titulo;
    private List<NuevaPreguntaDTO> preguntas;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<NuevaPreguntaDTO> getPreguntas() {
        return preguntas;
    }

    public void setPreguntas(List<NuevaPreguntaDTO> preguntas) {
        this.preguntas = preguntas;
    }

    public static NuevoFormularioDTOBuilder builder() {
        return new NuevoFormularioDTOBuilder();
    }

    public static final class NuevoFormularioDTOBuilder {
        private NuevoFormularioDTO nuevoFormularioDTO;

        private NuevoFormularioDTOBuilder() {
            nuevoFormularioDTO = new NuevoFormularioDTO();
        }

        public NuevoFormularioDTOBuilder titulo(String titulo) {
            nuevoFormularioDTO.setTitulo(titulo);
            return this;
        }

        public NuevoFormularioDTOBuilder preguntas(List<NuevaPreguntaDTO> preguntas) {
            nuevoFormularioDTO.setPreguntas(preguntas);
            return this;
        }

        public NuevoFormularioDTO build() {
            return nuevoFormularioDTO;
        }
    }

    public Formulario formularioFromDTO() {
        return Formulario.builder()
                .titulo(this.titulo)
                .preguntas(this.preguntas.stream().map(NuevaPreguntaDTO::preguntaFromDTO).collect(Collectors.toList()))
                .build();
    }
}
