package com.unqttip.agendaprofesional.controllers;

import com.unqttip.agendaprofesional.services.ArchivoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import org.springframework.core.io.Resource;

@RestController
@EnableAutoConfiguration
public class ArchivoController {

    @Autowired
    private ArchivoService archivoService;

    @PostMapping("/cargar_archivo")
    public void cargarArchivo(@RequestParam MultipartFile archivo, @RequestParam Long idPaciente) {
            archivoService.guardarArchivo(archivo, idPaciente);
    }

    @GetMapping("/descargar_archivo")
    public ResponseEntity<Resource> descargarArchivo(@RequestParam Long idArchivo) {
        Resource archivo = archivoService.descargarArchivo(idArchivo);

        String contentType = "application/octet-stream";
        String headerValue = "attachment; filename=\"" + archivo.getFilename() + "\"";

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, headerValue)
                .body(archivo);
    }

    /*
    @PutMapping("reemplazar_archivo")
    @DeleteMapping("borrar_archivo")
     */

}
