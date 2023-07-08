package com.unqttip.agendaprofesional.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.unqttip.agendaprofesional.dtos.PreguntaRespondidaDTO;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "respuestas")
public class Respuesta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String respuestaNombre;
    @ManyToOne(fetch = FetchType.LAZY)
    private Pregunta pregunta;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Formulario formulario;
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
    private LocalDateTime fecha;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRespuestaNombre() {
        return respuestaNombre;
    }

    public void setRespuestaNombre(String respuestaNombre) {
        this.respuestaNombre = respuestaNombre;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Formulario getFormulario() {
        return formulario;
    }

    public void setFormulario(Formulario formulario) {
        this.formulario = formulario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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

        public RespuestaBuilder respuestaNombre(String respuestaNombre) {
            respuesta.setRespuestaNombre(respuestaNombre);
            return this;
        }

        public RespuestaBuilder pregunta(Pregunta pregunta) {
            respuesta.setPregunta(pregunta);
            return this;
        }

        public RespuestaBuilder formulario(Formulario formulario) {
            respuesta.setFormulario(formulario);
            return this;
        }

        public RespuestaBuilder paciente(Paciente paciente) {
            respuesta.setPaciente(paciente);
            return this;
        }

        public RespuestaBuilder fecha(LocalDateTime fecha) {
            respuesta.setFecha(fecha);
            return this;
        }

        public Respuesta build() {
            return respuesta;
        }
    }

    public PreguntaRespondidaDTO fromModelToDTO() {
        return PreguntaRespondidaDTO.builder()
                .idPregunta(this.pregunta.getId())
                .preguntaNombre(this.pregunta.getPreguntaNombre())
                .obligatoria(this.pregunta.getObligatoria())
                .tipo(this.pregunta.getTipo().name())
                .opciones(this.pregunta.getOpciones())
                .respuestaNombre(this.respuestaNombre)
                .fecha(this.fecha.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))
                .build();
    }
}
