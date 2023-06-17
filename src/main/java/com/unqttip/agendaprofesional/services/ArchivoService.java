package com.unqttip.agendaprofesional.services;

import com.unqttip.agendaprofesional.dtos.ArchivosPaginaDTO;
import com.unqttip.agendaprofesional.exceptions.FileAlreadyUploadedException;
import com.unqttip.agendaprofesional.exceptions.NotFoundException;
import com.unqttip.agendaprofesional.exceptions.UploadFailedException;
import com.unqttip.agendaprofesional.model.Archivo;
import com.unqttip.agendaprofesional.model.Paciente;
import com.unqttip.agendaprofesional.model.Turno;
import com.unqttip.agendaprofesional.repositories.ArchivoDAO;
import com.unqttip.agendaprofesional.repositories.TurnoDAO;
import com.unqttip.agendaprofesional.utils.FileHandlerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Service
public class ArchivoService {

    @Autowired
    private ArchivoDAO archivoDAO;

    @Autowired
    private TurnoDAO turnoDAO;

    @Autowired
    private EntityManager entityManager;

    private FileHandlerUtil fileHandlerUtil= new FileHandlerUtil();

    public Long guardarArchivo(MultipartFile archivo, Long idPaciente) {
        String rutaArchivo="";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaActual = LocalDate.now();
        String fechaActualString = fechaActual.format(formatter);
        try {
            rutaArchivo = fileHandlerUtil.saveFile(archivo, idPaciente, fechaActualString);
        } catch (IOException exception) {
            throw new UploadFailedException("Ocurrio un error al intentar guardar el archivo. Vuelva a intentar en unos minutos");
        } catch (FileAlreadyUploadedException exception) {
            //En caso de catchear este error, preguntar en el front si desea reemplazar el archivo o cancelar la carga.
            throw new UploadFailedException("El archivo que intento cargar ya se encuentra en el historial del paciente.");
        }
        if(rutaArchivo == "") {
            throw new UploadFailedException("Error al generar la ruta del archivo");
        }

        Archivo nuevoArchivo = new Archivo();
        nuevoArchivo.setPaciente(entityManager.getReference(Paciente.class, idPaciente));
        nuevoArchivo.setFechaCarga(fechaActual);
        nuevoArchivo.setNombreArchivo(archivo.getOriginalFilename());
        return archivoDAO.save(nuevoArchivo).getId();
    }

    public void guardarArchivoTurno(MultipartFile archivo, Long idTurno) {
        Turno turnoDelArchivo = entityManager.getReference(Turno.class, idTurno);
        Paciente pacienteArchivo = null;
        try{
            pacienteArchivo = turnoDelArchivo.getPaciente();
        } catch (RuntimeException e) {
            throw new NotFoundException("No se encontro el turno para asociar");
        }
        Long idArchivo = null;
        try {
            idArchivo = guardarArchivo(archivo, pacienteArchivo.getId());
        } catch(UploadFailedException exception) {
            throw exception;
        }
        //El entityManager antes trajo una referencia sin la lista de turnos. Si no se limpia aca, se reusa y salta un error.
        entityManager.clear();
        //aca chequear que guardar me haya devuelto el id. Si no lo devuelve arrojar una excepcion y en front avisar que ya existe, y si quiere asociarlo
        asociarArchivoTurno(idArchivo, idTurno);
    }

    public Resource descargarArchivo(Long idArchivo) {
        Optional<Archivo> maybeArchivo = archivoDAO.findById(idArchivo);
        if(maybeArchivo.isEmpty()) {
            throw new NotFoundException("El archivo solicitado no existe");
        }
        Archivo registroArchivo = maybeArchivo.get();
        try {
            String rutaArchivo = registroArchivo.getPathCompleto();
            Resource archivo = fileHandlerUtil.downloadFileFrom(rutaArchivo);
            return archivo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void reemplazarArchivo(MultipartFile archivo, Long idPaciente, String fechaString) {
        String rutaArchivo="";
        try {
            rutaArchivo = fileHandlerUtil.saveFile(archivo, idPaciente, fechaString);
        } catch (IOException exception) {
            throw new UploadFailedException("Ocurrio un error al intentar guardar el archivo");
        }
        if(rutaArchivo == "") {
            throw new UploadFailedException("Error al generar la ruta del archivo");
        }

    }

    public void eliminarArchivo(Long idArchivo) {
        Optional<Archivo> maybeArchivo = archivoDAO.findById(idArchivo);
        if(maybeArchivo.isEmpty()) {
            throw new NotFoundException("El archivo solicitado no existe");
        }

        try {
            Archivo archivo = maybeArchivo.get();
            if(fileHandlerUtil.eliminarArchivo(archivo.getPathCompleto())) {
                archivoDAO.deleteById(idArchivo);
            }
        } catch (RuntimeException e) {
            throw new RuntimeException("Ocurrio un error al intentar eliminar el archivo");
        }
    }

    public ArchivosPaginaDTO getArchivosPaciente(Long pacienteId, Integer numeroPagina, String orderBy, boolean ascendingOrder) {
        Sort sortBy = Sort.by(orderBy);
        if(ascendingOrder) {
            sortBy.ascending();
        } else {
            sortBy.descending();
        }

        Pageable pageable = PageRequest.of(numeroPagina, 5, sortBy);

        Page<Archivo> archivos = archivoDAO.findByPacienteId(pacienteId, pageable);

        ArchivosPaginaDTO result = new ArchivosPaginaDTO();
        result.setArchivos(archivos.getContent());
        result.setPrimera(archivos.isFirst());
        result.setUltima(archivos.isLast());
        result.setCantidadPaginas(archivos.getTotalPages());

        //return archivoDAO.getArchivosPaginadosPaciente(pacienteId, numeroPagina, orderBy, ascendingOrder);

        return result;
    }

    public ArchivosPaginaDTO getArchivosTurno(Long turnoId, Integer numeroPagina, String orderBy, boolean ascendingOrder) {
        Sort sortBy = Sort.by(orderBy);
        if(ascendingOrder) {
            sortBy.ascending();
        } else {
            sortBy.descending();
        }

        Pageable pageable = PageRequest.of(numeroPagina, 5, sortBy);

        Turno turno = entityManager.getReference(Turno.class, turnoId);

        Page<Archivo> archivos = archivoDAO.findByTurnos(turno, pageable);

        ArchivosPaginaDTO result = new ArchivosPaginaDTO();
        result.setArchivos(archivos.getContent());
        result.setPrimera(archivos.isFirst());
        result.setUltima(archivos.isLast());
        result.setCantidadPaginas(archivos.getTotalPages());

        return result;
    }

    public void asociarArchivoTurno(Long archivoId, Long turnoId) {
        Optional<Archivo> maybeArchivo = archivoDAO.findById(archivoId);
        Turno turno = entityManager.getReference(Turno.class, turnoId);
        if(maybeArchivo.isEmpty()) {
            throw new NotFoundException("El archivo que se esta intentando agregar no existe");
        }
        Archivo archivo = maybeArchivo.get();
        archivo.getTurnos().add(turno);
        archivoDAO.save(archivo);
    }

    public void desasociarArchivoTurno(Long archivoId, Long turnoId) {
        Optional<Archivo> maybeArchivo = archivoDAO.findById(archivoId);
        Turno turno = entityManager.getReference(Turno.class, turnoId);
        if(maybeArchivo.isEmpty()) {
            throw new NotFoundException("El archivo que se esta intentando agregar no existe");
        }
        Archivo archivo = maybeArchivo.get();
        archivo.getTurnos().remove(turno);
        archivoDAO.save(archivo);
    }
}
