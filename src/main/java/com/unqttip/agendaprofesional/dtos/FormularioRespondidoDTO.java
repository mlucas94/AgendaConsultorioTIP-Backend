package com.unqttip.agendaprofesional.dtos;

import java.util.List;

public class FormularioRespondidoDTO {
    private Long idFormulario;
    private String titulo;
    private Long idPaciente;
    private Long idTurno;
    private List<PreguntaRespondidaDTO> preguntasRespondidas;

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
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

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
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

        public FormularioRespondidoDTOBuilder titulo(String titulo) {
            formularioRespondidoDTO.setTitulo(titulo);
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

        public FormularioRespondidoDTOBuilder idTurno(Long idTurno) {
            formularioRespondidoDTO.setIdTurno(idTurno);
            return this;
        }
    }
}
