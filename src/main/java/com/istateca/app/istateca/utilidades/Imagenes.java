package com.istateca.app.istateca.utilidades;


import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Imagenes {

    //ruta: especifica la ruta, identificador: es el nombre que contendra el archivo, imagen: archivo a subir
    public static String subirArchivo(String ruta, String identificador, MultipartFile archivo) {
        try {
            String nombreArchivo = identificador + ((archivo.getOriginalFilename()).substring((archivo.getOriginalFilename()).lastIndexOf(".")));
            //String filePath = ruta + File.separator + nombreArchivo; permite selecciona el separador correcto segun el sistema operativo
            String filePath = ruta + "/" + nombreArchivo;
            File targetFile = new File(filePath);
            FileCopyUtils.copy(archivo.getInputStream(), new FileOutputStream(targetFile));
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
            return "Error al subir el archivo.";
        }
    }

    //metodo para obtener imagen por enpoint , usar solo en caso de falla del path directo
    public static ResponseEntity<Resource> obtenerArchivo(String rutaArchivo, Integer tipo) {
        try {
            File file = new File(rutaArchivo);
            if (!file.exists()) {
                return ResponseEntity.notFound().build();
            }
            Path path = Paths.get(rutaArchivo);
            Resource resource = new FileSystemResource(path.toFile());

            if (tipo == 1 && rutaArchivo.substring(rutaArchivo.lastIndexOf(".") + 1).equalsIgnoreCase("png")) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_PNG)
                        .body((Resource) resource);
            } else if (tipo == 1 && rutaArchivo.substring(rutaArchivo.lastIndexOf(".") + 1).equalsIgnoreCase("jpg")) {
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG)
                        .body((Resource) resource);
            } else if (tipo == 2 && rutaArchivo.substring(rutaArchivo.lastIndexOf(".") + 1).equalsIgnoreCase("pdf")) {
                return ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_PDF)
                        .body((Resource) resource);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }
}
