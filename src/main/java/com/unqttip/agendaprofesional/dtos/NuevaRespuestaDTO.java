package com.unqttip.agendaprofesional.dtos;

public class NuevaRespuestaDTO {
    private Long idPregunta;
    private String respuestaNombre;

    public Long getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Long idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getRespuestaNombre() {
        return respuestaNombre;
    }

    public void setRespuestaNombre(String respuestaNombre) {
        this.respuestaNombre = respuestaNombre;
    }

    public static NuevaRespuestaDTOBuilder builder() {
        return new NuevaRespuestaDTOBuilder();
    }

    public static final class NuevaRespuestaDTOBuilder {
        private NuevaRespuestaDTO nuevaRespuestaDTO;

        private NuevaRespuestaDTOBuilder() {
            nuevaRespuestaDTO = new NuevaRespuestaDTO();
        }

        public NuevaRespuestaDTOBuilder idPregunta(Long idPregunta) {
            nuevaRespuestaDTO.setIdPregunta(idPregunta);
            return this;
        }

        public NuevaRespuestaDTOBuilder respuestaNombre(String respuestaNombre) {
            nuevaRespuestaDTO.setRespuestaNombre(respuestaNombre);
            return this;
        }

        public NuevaRespuestaDTO build() {
            return nuevaRespuestaDTO;
        }
    }
}
