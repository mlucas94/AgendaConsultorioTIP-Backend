package com.unqttip.agendaprofesional.model;

import javax.persistence.*;

@Entity
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respuestaTexto;
    @ManyToOne
    private Pregunta pregunta;
    /*@ManyToOne
    private Formulario formulario;*/
    @ManyToOne
    private Paciente paciente;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuestaTexto() {
        return respuestaTexto;
    }

    public void setRespuestaTexto(String respuestaTexto) {
        this.respuestaTexto = respuestaTexto;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public static RespuestaBuilder builder() {
        return new RespuestaBuilder();
    }

    public static final class RespuestaBuilder {
        private Respuesta respuesta;

        private RespuestaBuilder() {
            respuesta = new Respuesta();
        }

        public RespuestaBuilder id(Long id) {
            respuesta.setId(id);
            return this;
        }

        public RespuestaBuilder respuestaTexto(String respuestaTexto) {
            respuesta.setRespuestaTexto(respuestaTexto);
            return this;
        }

        public RespuestaBuilder pregunta(Pregunta pregunta) {
            respuesta.setPregunta(pregunta);
            return this;
        }

        public RespuestaBuilder paciente(Paciente paciente) {
            respuesta.setPaciente(paciente);
            return this;
        }

        public Respuesta build() {
            return respuesta;
        }
    }
}