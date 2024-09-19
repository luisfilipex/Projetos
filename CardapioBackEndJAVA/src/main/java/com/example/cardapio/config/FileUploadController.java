package com.example.cardapio.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileUploadController {

    @Value("${upload.dir}")
    private String UPLOAD_DIR;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            Path path = Paths.get(UPLOAD_DIR);
            if (!Files.exists(path)) {
                Files.createDirectories(path);
            }

            Path filePath = path.resolve(file.getOriginalFilename());
            file.transferTo(filePath.toFile());

            return ResponseEntity.ok("Arquivo carregado com sucesso: " + filePath.toString());
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Erro ao fazer upload do arquivo: " + e.getMessage());
        }
    }
}
