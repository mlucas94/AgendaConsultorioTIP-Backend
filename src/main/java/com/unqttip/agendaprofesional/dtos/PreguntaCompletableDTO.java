package com.unqttip.agendaprofesional.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PreguntaCompletableDTO {
    private Long id;
    @JsonProperty("pregunta_nombre")
    private String pregunta_nombre;
    private Boolean obligatoria;

    @JsonProperty("tipo")
    private String tipo;

    @JsonProperty("lista_opciones")
    private List<String> opciones;

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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<String> getOpciones() {
        return opciones;
    }

    public void setOpciones(List<String> opciones) {
        this.opciones = opciones;
    }

    public static PreguntaCompletableDTOBuilder builder() {
        return new PreguntaCompletableDTOBuilder();
    }

    public static final class PreguntaCompletableDTOBuilder {
        private PreguntaCompletableDTO preguntaCompletableDTO;

        private PreguntaCompletableDTOBuilder() {
            preguntaCompletableDTO = new PreguntaCompletableDTO();
        }

        public PreguntaCompletableDTOBuilder id(Long id) {
            preguntaCompletableDTO.setId(id);
            return this;
        }

        public PreguntaCompletableDTOBuilder pregunta_nombre(String pregunta_nombre) {
            preguntaCompletableDTO.setPregunta_nombre(pregunta_nombre);
            return this;
        }

        public PreguntaCompletableDTOBuilder obligatoria(Boolean obligatoria) {
            preguntaCompletableDTO.setObligatoria(obligatoria);
            return this;
        }

        public PreguntaCompletableDTOBuilder tipo(String tipoDeRespuesta) {
            preguntaCompletableDTO.setTipo(tipoDeRespuesta);
            return this;
        }

        public PreguntaCompletableDTOBuilder opciones(List<String> opciones) {
            preguntaCompletableDTO.setOpciones(opciones);
            return this;
        }

        public PreguntaCompletableDTO build() {
            return preguntaCompletableDTO;
        }
    }
}
