package com.unqttip.agendaprofesional.dtos;

import java.util.List;

public class FormularioRespondidoDTO {
    private Long idFormulario;
    private String tituloFormulario;
    private Long idPaciente;
    private List<PreguntaRespondidaDTO> preguntasRespondidas;

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getTituloFormulario() {
        return tituloFormulario;
    }

    public void setTituloFormulario(String tituloFormulario) {
        this.tituloFormulario = tituloFormulario;
    }

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public List<PreguntaRespondidaDTO> getPreguntasRespondidas() {
        return preguntasRespondidas;
    }

    public void setPreguntasRespondidas(List<PreguntaRespondidaDTO> preguntasRespondidas) {
        this.preguntasRespondidas = preguntasRespondidas;
    }

    public static FormularioRespondidoDTOBuilder builder() {
        return new FormularioRespondidoDTOBuilder();
    }

    public static final class FormularioRespondidoDTOBuilder {
        private FormularioRespondidoDTO formularioRespondidoDTO;

        private FormularioRespondidoDTOBuilder() {
            formularioRespondidoDTO = new FormularioRespondidoDTO();
        }

        public FormularioRespondidoDTOBuilder idFormulario(Long idFormulario) {
            formularioRespondidoDTO.setIdFormulario(idFormulario);
            return this;
        }

        public FormularioRespondidoDTOBuilder tituloFormulario(String tituloFormulario) {
            formularioRespondidoDTO.setTituloFormulario(tituloFormulario);
            return this;
        }

        public FormularioRespondidoDTOBuilder idPaciente(Long idPaciente) {
            formularioRespondidoDTO.setIdPaciente(idPaciente);
            return this;
        }

        public FormularioRespondidoDTOBuilder preguntasRespondidas(List<PreguntaRespondidaDTO> preguntasRespondidas) {
            formularioRespondidoDTO.setPreguntasRespondidas(preguntasRespondidas);
            return this;
        }

        public FormularioRespondidoDTO build() {
            return formularioRespondidoDTO;
        }
    }
}
