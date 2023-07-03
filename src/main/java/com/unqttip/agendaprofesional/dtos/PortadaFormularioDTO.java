package com.unqttip.agendaprofesional.dtos;

public class PortadaFormularioDTO {
    private Long id;
    private String titulo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public static PortadaFormularioDTOBuilder builder() {
        return new PortadaFormularioDTOBuilder();
    }

    public static final class PortadaFormularioDTOBuilder {
        private PortadaFormularioDTO portadaFormularioDTO;

        private PortadaFormularioDTOBuilder() {
            portadaFormularioDTO = new PortadaFormularioDTO();
        }

        public PortadaFormularioDTOBuilder id(Long id) {
            portadaFormularioDTO.setId(id);
            return this;
        }

        public PortadaFormularioDTOBuilder titulo(String titulo) {
            portadaFormularioDTO.setTitulo(titulo);
            return this;
        }

        public PortadaFormularioDTO build() {
            return portadaFormularioDTO;
        }
    }
}
