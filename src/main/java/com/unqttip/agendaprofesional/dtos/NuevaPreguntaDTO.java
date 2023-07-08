package com.unqttip.agendaprofesional.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unqttip.agendaprofesional.model.Pregunta;
import com.unqttip.agendaprofesional.model.TipoDeRespuesta;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class NuevaPreguntaDTO {
    @JsonProperty("pregunta_nombre")
    private String preguntaNombre;
    private Boolean obligatoria;
    private String tipo;
    @JsonProperty("lista_opciones")
    private List<String> opciones;

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

    public static NuevaPreguntaDTOBuilder builder() {
        return new NuevaPreguntaDTOBuilder();
    }

    public static final class NuevaPreguntaDTOBuilder {
        private NuevaPreguntaDTO nuevaPreguntaDTO;

        private NuevaPreguntaDTOBuilder() {
            nuevaPreguntaDTO = new NuevaPreguntaDTO();
        }

        public NuevaPreguntaDTOBuilder preguntaNombre(String preguntaNombre) {
            nuevaPreguntaDTO.setPreguntaNombre(preguntaNombre);
            return this;
        }

        public NuevaPreguntaDTOBuilder obligatoria(Boolean obligatoria) {
            nuevaPreguntaDTO.setObligatoria(obligatoria);
            return this;
        }

        public NuevaPreguntaDTOBuilder tipo(String tipo) {
            nuevaPreguntaDTO.setTipo(tipo);
            return this;
        }

        public NuevaPreguntaDTOBuilder opciones(List<String> opciones) {
            nuevaPreguntaDTO.setOpciones(opciones);
            return this;
        }

        public NuevaPreguntaDTO build() {
            return nuevaPreguntaDTO;
        }
    }

    public Pregunta preguntaFromDTO() {
        return Pregunta.builder()
                .preguntaNombre(this.preguntaNombre)
                .obligatoria(this.obligatoria)
                .tipo(TipoDeRespuesta.valueOf(this.tipo.toUpperCase(Locale.ROOT)))
                .opciones(this.opciones.stream()
                        .map(String::valueOf)
                        .collect(Collectors.joining(";", "", "")))
                .build();
    }
}
