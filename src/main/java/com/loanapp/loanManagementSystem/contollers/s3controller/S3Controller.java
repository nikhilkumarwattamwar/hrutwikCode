package com.loanapp.loanManagementSystem.contollers.s3controller;

import com.loanapp.loanManagementSystem.service.s3service.S3Service;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/s3")
public class S3Controller {


   final private S3Service s3Service;

    public S3Controller(S3Service s3Service) {
        this.s3Service = s3Service;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam("file") MultipartFile file)
            throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String fileName = s3Service.uploadFile(file);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(fileName);
    }





    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> download(@PathVariable String fileName) {

        byte[] data = s3Service.downloadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + fileName + "\"")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE)
                .header("X-File-Name", fileName) // optional custom header
                .body(data);
    }



    @PutMapping("/update/{fileName}")
    public ResponseEntity<String> update(
            @PathVariable String fileName,
            @RequestParam("file") MultipartFile file) throws IOException {

        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        s3Service.updateFile(fileName, file);

        return ResponseEntity
                .ok("File updated successfully: " + fileName);
    }


    @DeleteMapping("/delete/{fileName}")
    public ResponseEntity<Void> delete(@PathVariable String fileName) {

        s3Service.deleteFile(fileName);

        return ResponseEntity.noContent().build();
    }

}
