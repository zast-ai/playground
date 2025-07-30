package com.example.testpath.Controller.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/benchmark02")
public class Benchmark02 {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String BASE_PATH = System.getProperty("user.dir");

    @GetMapping("/file")
    public ResponseEntity<String> getFileContent(
            @RequestParam String directory,
            @RequestParam String filename) {
        try {
            Path basePath = Paths.get(BASE_PATH).toAbsolutePath().normalize();
            Path dirPath = basePath.resolve(directory).normalize();
            Path filePath = dirPath.resolve(filename);

            if (!Files.exists(filePath) || !Files.isRegularFile(filePath)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("File not found in the specified directory.");
            }
            String content = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            return ResponseEntity.ok(content);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error reading file: " + e.getMessage());
        }
    }
}
