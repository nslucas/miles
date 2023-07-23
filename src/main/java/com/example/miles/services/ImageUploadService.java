package com.example.miles.services;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageUploadService {
    private static final String UPLOAD_DIR = "C:/Users/lucas/DEV/Projetos/Miles/src/main/java/com/example/miles/resources/images/";

    public String uploadImage(MultipartFile image) throws IOException {
        String imageName = UUID.randomUUID().toString() + "_" + image.getOriginalFilename();
        String imagePath = UPLOAD_DIR + imageName;

        // Realize o upload da imagem para o diretório especificado
        File path = new File(imagePath);
        image.transferTo(path);

        return imageName;
    }

}
