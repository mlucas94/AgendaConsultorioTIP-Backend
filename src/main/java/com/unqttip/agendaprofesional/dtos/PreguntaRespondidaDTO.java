package com.unqttip.agendaprofesional.dtos;

import java.time.LocalDateTime;

public class PreguntaRespondidaDTO {
    private Long idPregunta;
    private String preguntaNombre;
    private Boolean obligatoria;
    private String tipo;
    private String opciones;
    private String respuestaNombre;
    private String fecha;

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getOpciones() {
        return opciones;
    }

    public void setOpciones(String opciones) {
        this.opciones = opciones;
    }

    public String getRespuestaNombre() {
        return respuestaNombre;
    }

    public void setRespuestaNombre(String respuestaNombre) {
        this.respuestaNombre = respuestaNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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

        public PreguntaRespondidaDTOBuilder preguntaNombre(String preguntaNombre) {
            preguntaRespondidaDTO.setPreguntaNombre(preguntaNombre);
            return this;
        }

        public PreguntaRespondidaDTOBuilder obligatoria(Boolean obligatoria) {
            preguntaRespondidaDTO.setObligatoria(obligatoria);
            return this;
        }

        public PreguntaRespondidaDTOBuilder tipo(String tipo) {
            preguntaRespondidaDTO.setTipo(tipo);
            return this;
        }

        public PreguntaRespondidaDTOBuilder opciones(String opciones) {
            preguntaRespondidaDTO.setOpciones(opciones);
            return this;
        }

        public PreguntaRespondidaDTOBuilder respuestaNombre(String respuestaNombre) {
            preguntaRespondidaDTO.setRespuestaNombre(respuestaNombre);
            return this;
        }

        public PreguntaRespondidaDTOBuilder fecha(String fecha) {
            preguntaRespondidaDTO.setFecha(fecha);
            return this;
        }

        public PreguntaRespondidaDTO build() {
            return preguntaRespondidaDTO;
        }
    }
}
