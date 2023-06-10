package com.unqttip.agendaprofesional.dtos;

import com.unqttip.agendaprofesional.model.Archivo;

import java.util.List;
import java.util.Set;

public class ArchivosPaginaDTO {

    Integer cantidadPaginas;

    boolean ultima;

    boolean primera;

    List<Archivo> archivos;

    public Integer getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(Integer cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public boolean isUltima() {
        return ultima;
    }

    public void setUltima(boolean ultima) {
        this.ultima = ultima;
    }

    public boolean isPrimera() {
        return primera;
    }

    public void setPrimera(boolean primera) {
        this.primera = primera;
    }

    public List<Archivo> getArchivos() {
        return archivos;
    }

    public void setArchivos(List<Archivo> archivos) {
        this.archivos = archivos;
    }
}
