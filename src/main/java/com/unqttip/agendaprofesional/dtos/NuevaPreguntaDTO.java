package com.unqttip.agendaprofesional.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.unqttip.agendaprofesional.model.Pregunta;
import com.unqttip.agendaprofesional.model.TipoDeRespuesta;

import java.util.Locale;

public class NuevaPreguntaDTO {
    private Long id;
    @JsonProperty("pregunta_nombre")
    private String pregunta_nombre;
    private Boolean obligatoria;

    @JsonProperty("tipo")
    private String tipoDeRespuesta;

    @JsonProperty("lista_opciones")
    private String opciones;

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

    public String getTipoDeRespuesta() {
        return tipoDeRespuesta;
    }

    public void setTipoDeRespuesta(String tipoDeRespuesta) {
        this.tipoDeRespuesta = tipoDeRespuesta;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public Pregunta preguntaFromDTO() {
        Pregunta pregunta = Pregunta.builder()
                .preguntaNombre(this.pregunta_nombre)
                .obligatoria(this.obligatoria)
                .tipoDeRespuesta(TipoDeRespuesta.valueOf(this.tipoDeRespuesta.toUpperCase(Locale.ROOT)))
                .opciones(this.opciones)
                .build();
        if (this.id != null) {
            pregunta.setId(id);
        }
        return pregunta;
    }

    public static NuevaPreguntaDTOBuilder builder() {
        return new NuevaPreguntaDTOBuilder();
    }

    public static final class NuevaPreguntaDTOBuilder {
        private NuevaPreguntaDTO nuevaPreguntaDTO;

        private NuevaPreguntaDTOBuilder() {
            nuevaPreguntaDTO = new NuevaPreguntaDTO();
        }

        public NuevaPreguntaDTOBuilder id(Long id) {
            nuevaPreguntaDTO.setId(id);
            return this;
        }

        public NuevaPreguntaDTOBuilder pregunta_nombre(String pregunta_nombre) {
            nuevaPreguntaDTO.setPregunta_nombre(pregunta_nombre);
            return this;
        }

        public NuevaPreguntaDTOBuilder obligatoria(Boolean obligatoria) {
            nuevaPreguntaDTO.setObligatoria(obligatoria);
            return this;
        }

        public NuevaPreguntaDTOBuilder tipoDeRespuesta(String tipoDeRespuesta) {
            nuevaPreguntaDTO.setTipoDeRespuesta(tipoDeRespuesta);
            return this;
        }

        public NuevaPreguntaDTOBuilder opciones(String opciones) {
            nuevaPreguntaDTO.setOpciones(opciones);
            return this;
        }

        public NuevaPreguntaDTO build() {
            return nuevaPreguntaDTO;
        }
    }
}
