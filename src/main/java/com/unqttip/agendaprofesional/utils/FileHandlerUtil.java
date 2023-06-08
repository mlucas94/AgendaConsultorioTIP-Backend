package com.unqttip.agendaprofesional.utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import com.unqttip.agendaprofesional.model.Archivo;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileHandlerUtil {

    private static String localFileServerPath = "D:\\temp\\";
    public static String saveFile( MultipartFile multipartFile, Long idPaciente, String currentDate)
            throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String paciente = Long.toString(idPaciente);

        String pathToFile = getPathname(fileName, paciente, currentDate);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = Paths.get(pathToFile);
            if (!Files.exists(filePath)) {
                Files.createDirectories(filePath);
            }
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("No se pudo guardar el archivo: " + fileName, ioe);
        }

        return pathToFile;
    }

    public static Resource downloadFileFrom(String filePath) throws IOException {
        Path path = Paths.get(localFileServerPath+filePath);

        return new UrlResource(path.toUri());
    }

    public static String getPathname(String fileName, String paciente, String currentDate) {
        return localFileServerPath + currentDate + "\\" + paciente + "\\" + fileName;
    }

    public boolean eliminarArchivo(String path) {
        String pathToFile = localFileServerPath + path;
        File file = new File(pathToFile);
        return file.delete();
    }
}
