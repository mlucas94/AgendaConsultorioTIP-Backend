package com.unqttip.agendaprofesional.utils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileHandlerUtil {

    private static String localFileServerPath = "D:\\temp\\";
    public static String saveFile( MultipartFile multipartFile, Long idPaciente)
            throws IOException {
        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

        String paciente = Long.toString(idPaciente);

        String pathToFile = getPathname(fileName, paciente);

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
        Path path = Paths.get(filePath);

        return new UrlResource(path.toUri());
    }

    public static String getPathname(String fileName, String paciente) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String currentDate = LocalDate.now().format(formatter);
        return localFileServerPath + currentDate + "\\" + paciente + "\\" + fileName;
    }

}
