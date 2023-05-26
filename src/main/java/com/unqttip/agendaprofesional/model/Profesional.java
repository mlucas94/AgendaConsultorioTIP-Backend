package com.unqttip.agendaprofesional.model;

import javax.persistence.*;

@Entity
@Table(name = "profesionales")
public class Profesional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50, unique = true)
    private String email;

    @Column(nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 64)
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public static ProfesionalBuilder builder() {
        return new ProfesionalBuilder();
    }

    public static final class ProfesionalBuilder {
        private Profesional profesional;

        private ProfesionalBuilder() {
            profesional = new Profesional();
        }

        public ProfesionalBuilder id(Long id) {
            profesional.setId(id);
            return this;
        }

        public ProfesionalBuilder email(String email) {
            profesional.setEmail(email);
            return this;
        }

        public ProfesionalBuilder nombre(String nombre) {
            profesional.setNombre(nombre);
            return this;
        }

        public ProfesionalBuilder password(String password) {
            profesional.setPassword(password);
            return this;
        }

        public Profesional build() {
            return profesional;
        }
    }
}
