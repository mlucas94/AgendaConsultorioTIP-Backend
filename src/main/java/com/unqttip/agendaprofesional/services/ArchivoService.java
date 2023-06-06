package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.exceptions.UploadFailedException;
import com.unqttip.agendaprofesional.model.Archivo;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.repositories.ArchivoDAO;
import com.unqttip.agendaprofesional.utils.FileHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.util.Optional;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoDAO archivoDAO;

    @Autowired
    private EntityManager entityManager;

    private FileHandlerUtil fileHandlerUtil= new FileHandlerUtil();

    public void guardarArchivo(MultipartFile archivo, Long idPaciente) {
        String rutaArchivo="";
        try {
            rutaArchivo = fileHandlerUtil.saveFile(archivo, idPaciente);
        } catch (IOException exception) {
            throw new UploadFailedException("Ocurrio un error al intentar guardar el archivo");
        }
        if(rutaArchivo == "") {
            throw new UploadFailedException("Error al generar la ruta del archivo");
        }
        Archivo nuevoArchivo = new Archivo();
        nuevoArchivo.setPaciente(entityManager.getReference(Paciente.class, idPaciente));
        nuevoArchivo.setPath(rutaArchivo);
        archivoDAO.save(nuevoArchivo);
    }

    public Resource descargarArchivo(Long idArchivo) {
        Optional<Archivo> maybeArchivo = archivoDAO.findById(idArchivo);
        if(maybeArchivo.isEmpty()) {
            throw new NotFoundException("El archivo solicitado no existe");
        }
        Archivo registroArchivo = maybeArchivo.get();
        try {
            String rutaArchivo = registroArchivo.getPath();
            Resource archivo = fileHandlerUtil.downloadFileFrom(rutaArchivo);
            return archivo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
