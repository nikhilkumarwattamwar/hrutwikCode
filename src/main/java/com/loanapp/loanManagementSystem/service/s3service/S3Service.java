package com.loanapp.loanManagementSystem.service.s3service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.*;

import java.io.IOException;
@Service
public class S3Service {

    private final S3Client s3Client;

    @Value("${aws.s3.bucket-name}")
    private String bucketName;

    public S3Service(S3Client s3Client) {
        this.s3Client = s3Client;
    }

    @PostConstruct
    public void init() {
        System.out.println("S3Service loaded, bucket = " + bucketName);
    }

//    public String uploadFile(MultipartFile file) throws IOException {
//
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key("loanphotos/"+  file.getOriginalFilename())
//                .contentType(file.getContentType())
//                .build();
//
//        s3Client.putObject(
//                request,
//                RequestBody.fromBytes(file.getBytes())
//        );
//
//        return "File uploaded successfully";
//    }


    public String uploadFile(MultipartFile file, Long homeLoanId, String documentType) throws IOException {

        String fileKey = "loanphotos/" + homeLoanId + "/" + documentType + "_" + file.getOriginalFilename();

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileKey)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

        return fileKey;
    }




//    public byte[] downloadFile(String fileName) {
//
//        GetObjectRequest request = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key("loanphotos/"+  fileName)
//                .build();
//
//        ResponseBytes<GetObjectResponse> objectBytes =
//                s3Client.getObjectAsBytes(request);
//
//        return objectBytes.asByteArray();
//    }

//    public byte[] downloadFileByPath(String filePath) {
//
//        GetObjectRequest request = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(filePath)
//                .build();
//
//        ResponseBytes<GetObjectResponse> objectBytes =
//                s3Client.getObjectAsBytes(request);
//
//        return objectBytes.asByteArray();
//    }


    public byte[] downloadFileByPath(String filePath) {

        if (filePath == null || filePath.isBlank()) {
            throw new IllegalArgumentException("File path is empty");
        }

        // ✅ Allow only Aadhaar photos
//        if (!filePath.contains("/AADHAAR")) {
//            throw new RuntimeException("Access denied: Only AADHAAR documents are allowed");
//        }

        try {
            GetObjectRequest request = GetObjectRequest.builder()
                    .bucket(bucketName)
                    .key(filePath)
                    .build();

            ResponseBytes<GetObjectResponse> objectBytes =
                    s3Client.getObjectAsBytes(request);

            return objectBytes.asByteArray();

        } catch (NoSuchKeyException e) {
            throw new RuntimeException("File not found in S3", e);

        } catch (S3Exception e) {
            throw new RuntimeException("Error downloading file from S3", e);
        }
    }



    public String updateFile(String fileName, MultipartFile file) throws IOException {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("loanphotos/"+   fileName)
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

        return "File updated successfully";
    }

    public String deleteFile(String fileName) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key("loanphotos/"+   fileName)
                .build();

        s3Client.deleteObject(request);

        return "File deleted successfully";
    }
}
