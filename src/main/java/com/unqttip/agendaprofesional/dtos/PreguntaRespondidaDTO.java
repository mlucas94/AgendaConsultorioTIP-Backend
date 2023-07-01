package com.unqttip.agendaprofesional.dtos;

import com.unqttip.agendaprofesional.model.TipoDeRespuesta;

public class PreguntaRespondidaDTO {
    private Long idPregunta;
    private String nombre;
    private Boolean obligatoria;
    private TipoDeRespuesta tipoDeRespuesta;
    private String opciones;
    private String respuesta;

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public static PreguntaRespondidaDTOBuilder builder() {
        return new PreguntaRespondidaDTOBuilder();
    }

    public static final class PreguntaRespondidaDTOBuilder {
        private PreguntaRespondidaDTO preguntaRespondidaDTO;

        private PreguntaRespondidaDTOBuilder() {
            preguntaRespondidaDTO = new PreguntaRespondidaDTO();
        }

        public PreguntaRespondidaDTOBuilder idPregunta(Long idPregunta) {
            preguntaRespondidaDTO.setIdPregunta(idPregunta);
            return this;
        }

        public PreguntaRespondidaDTOBuilder nombre(String nombre) {
            preguntaRespondidaDTO.setNombre(nombre);
            return this;
        }

        public PreguntaRespondidaDTOBuilder obligatoria(Boolean obligatoria) {
            preguntaRespondidaDTO.setObligatoria(obligatoria);
            return this;
        }

        public PreguntaRespondidaDTOBuilder tipoDeRespuesta(TipoDeRespuesta tipoDeRespuesta) {
            preguntaRespondidaDTO.setTipoDeRespuesta(tipoDeRespuesta);
            return this;
        }

        public PreguntaRespondidaDTOBuilder opciones(String opciones) {
            preguntaRespondidaDTO.setOpciones(opciones);
            return this;
        }

        public PreguntaRespondidaDTOBuilder respuesta(String respuesta) {
            preguntaRespondidaDTO.setRespuesta(respuesta);
            return this;
        }

        public PreguntaRespondidaDTO build() {
            return preguntaRespondidaDTO;
        }
    }
}
