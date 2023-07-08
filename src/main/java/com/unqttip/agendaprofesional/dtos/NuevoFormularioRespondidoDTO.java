package com.unqttip.agendaprofesional.dtos;

import java.util.List;

public class NuevoFormularioRespondidoDTO {
    private Long idPaciente;
    private Long idFormulario;
    private List<NuevaRespuestaDTO> nuevasRespuestas;

    private Long idTurno;

    public Long getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(Long idPaciente) {
        this.idPaciente = idPaciente;
    }

    public Long getIdFormulario() {
        return idFormulario;
    }

    public void setIdFormulario(Long idFormulario) {
        this.idFormulario = idFormulario;
    }

    public List<NuevaRespuestaDTO> getNuevasRespuestas() {
        return nuevasRespuestas;
    }

    public void setNuevasRespuestas(List<NuevaRespuestaDTO> nuevasRespuestas) {
        this.nuevasRespuestas = nuevasRespuestas;
    }

    public Long getIdTurno() {
        return idTurno;
    }

    public void setIdTurno(Long idTurno) {
        this.idTurno = idTurno;
    }

    public static NuevoFormularioRespondidoDTOBuilder builder() {
        return new NuevoFormularioRespondidoDTOBuilder();
    }

    public static final class NuevoFormularioRespondidoDTOBuilder {
        private NuevoFormularioRespondidoDTO nuevoFormularioRespondidoDTO;

        private NuevoFormularioRespondidoDTOBuilder() {
            nuevoFormularioRespondidoDTO = new NuevoFormularioRespondidoDTO();
        }

        public NuevoFormularioRespondidoDTOBuilder idPaciente(Long idPaciente) {
            nuevoFormularioRespondidoDTO.setIdPaciente(idPaciente);
            return this;
        }

        public NuevoFormularioRespondidoDTOBuilder idFormulario(Long idFormulario) {
            nuevoFormularioRespondidoDTO.setIdFormulario(idFormulario);
            return this;
        }

        public NuevoFormularioRespondidoDTOBuilder nuevasRespuestas(List<NuevaRespuestaDTO> nuevasRespuestas) {
            nuevoFormularioRespondidoDTO.setNuevasRespuestas(nuevasRespuestas);
            return this;
        }

        public NuevoFormularioRespondidoDTO build() {
            return nuevoFormularioRespondidoDTO;
        }
    }
}
