package com.unqttip.agendaprofesional.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class FormularioString {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String form;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getForm() {
        return form;
    }

    public void setForm(String form) {
        this.form = form;
    }

    public static FormularioStringBuilder builder() {
        return new FormularioStringBuilder();
    }

    public static final class FormularioStringBuilder {
        private FormularioString formularioString;

        private FormularioStringBuilder() {
            formularioString = new FormularioString();
        }

        public FormularioStringBuilder id(Long id) {
            formularioString.setId(id);
            return this;
        }

        public FormularioStringBuilder form(String form) {
            formularioString.setForm(form);
            return this;
        }

        public FormularioString build() {
            return formularioString;
        }
    }
}
