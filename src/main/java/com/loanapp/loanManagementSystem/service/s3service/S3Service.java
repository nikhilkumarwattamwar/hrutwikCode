package com.loanapp.loanManagementSystem.service.s3service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.ResponseBytes;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.DeleteObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
//
//@Service
//public class S3Service {
//
//    @Autowired
//    private S3Client s3Client;
//
//    @Value("${aws.s3.bucket}")
//    private String bucketName;
//
//    public String uploadFile(MultipartFile file) throws IOException {
//
//        PutObjectRequest request = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(file.getOriginalFilename())
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
//
//}




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

    public String uploadFile(MultipartFile file) throws IOException {

        PutObjectRequest request = PutObjectRequest.builder()
                .bucket(bucketName)
                .key("loanphotos/"+  file.getOriginalFilename())
                .contentType(file.getContentType())
                .build();

        s3Client.putObject(
                request,
                RequestBody.fromBytes(file.getBytes())
        );

        return "File uploaded successfully";
    }


    /* ================= DOWNLOAD ================= */
    public byte[] downloadFile(String fileName) {

        GetObjectRequest request = GetObjectRequest.builder()
                .bucket(bucketName)
                .key("loanphotos/"+  fileName)
                .build();

        ResponseBytes<GetObjectResponse> objectBytes =
                s3Client.getObjectAsBytes(request);

        return objectBytes.asByteArray();
    }

    /* ================= UPDATE ================= */
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

    /* ================= DELETE ================= */
    public String deleteFile(String fileName) {

        DeleteObjectRequest request = DeleteObjectRequest.builder()
                .bucket(bucketName)
                .key("loanphotos/"+   fileName)
                .build();

        s3Client.deleteObject(request);

        return "File deleted successfully";
    }
}
