package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.dtos.ArchivosPaginaDTO;
import com.unqttip.agendaprofesional.model.Archivo;
import com.unqttip.agendaprofesional.services.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

import java.util.List;

@RestController
@EnableAutoConfiguration
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @PostMapping("/archivo/cargar")
    public void cargarArchivo(@RequestParam MultipartFile archivo, @RequestParam Long idPaciente) {
            archivoService.guardarArchivo(archivo, idPaciente);
    }

    @PostMapping("/archivo/cargar/turno")
    public void cargarArchivoTurno(@RequestParam MultipartFile archivo, @RequestParam Long idTurno) {
        archivoService.guardarArchivoTurno(archivo, idTurno);
    }

    @GetMapping("/archivo/descargar")
    public ResponseEntity<Resource> descargarArchivo(@RequestParam Long idArchivo) {
        Resource archivo = archivoService.descargarArchivo(idArchivo);

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + archivo.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(archivo);
    }

    @PutMapping("archivo/reemplazar")
    public void reemplazarArchivo(@RequestParam MultipartFile archivo, @RequestParam Long idPaciente, @RequestParam String fecha) {
        archivoService.reemplazarArchivo(archivo, idPaciente, fecha);
    }

    @DeleteMapping("archivo/eliminar")
    @CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.PUT,RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
    public void eliminarArchivo(@RequestParam Long idArchivo) {
        archivoService.eliminarArchivo(idArchivo);
    }

    @GetMapping("/archivos/paciente")
    public ResponseEntity<ArchivosPaginaDTO> getArchivosPaciente(@RequestParam Long pacienteId, @RequestParam Integer numeroPagina, @RequestParam String orderBy, @RequestParam boolean ascendingOrder) {
        ArchivosPaginaDTO archivos = archivoService.getArchivosPaciente(pacienteId, numeroPagina, orderBy, ascendingOrder);
        return ResponseEntity.ok().body(archivos);
    }

    @PostMapping("/archivo/turno")
    public void asociarArchivoTurno(@RequestParam Long archivoId, @RequestParam Long turnoId) {
        archivoService.asociarArchivoTurno(archivoId, turnoId);
    }

    @DeleteMapping("/archivo/turno")
    public void desasociarArchivoTurno(@RequestParam Long archivoId, @RequestParam Long turnoId) {
        archivoService.desasociarArchivoTurno(archivoId, turnoId);
    }

    @GetMapping("/archivos/turno")
    public ResponseEntity<ArchivosPaginaDTO> getArchivosTurno(@RequestParam Long turnoId, @RequestParam Integer numeroPagina, @RequestParam String orderBy, @RequestParam boolean ascendingOrder) {
        ArchivosPaginaDTO archivos = archivoService.getArchivosTurno(turnoId, numeroPagina, orderBy, ascendingOrder);
        return ResponseEntity.ok().body(archivos);
    }

}
