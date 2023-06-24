package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.model.FormularioCompleto;
import com.unqttip.agendaprofesional.repositories.FormularioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FormularioService {

    @Autowired
    private FormularioDAO formularioDAO;

    public void guardarRespuestas(FormularioCompleto formularioCompleto) {
        formularioDAO.save(formularioCompleto);
    }

    public FormularioCompleto getFormularioCompleto(Long id) {
        Optional<FormularioCompleto> maybeFormulario = formularioDAO.findById(id);
        if(maybeFormulario.isEmpty()) {
            throw new NotFoundException("No se encontro el formulario solicitado");
        }
        return maybeFormulario.get();
    }
}
