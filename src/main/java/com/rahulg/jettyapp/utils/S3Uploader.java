package com.rahulg.jettyapp.utils;


import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;

import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;


public class S3Uploader {

    private static final String BUCKET_NAME = "mytest-bucket-rhgoyal";

    final S3Client s3Client;
    final S3Presigner s3Presigner;


    public S3Uploader() {
        System.out.println("Initializing S3 Client");
        s3Client = S3Client.builder()
                .region(Region.AP_SOUTH_1)
                .credentialsProvider(DefaultCredentialsProvider.create())
                //.overrideConfiguration()
                .build();
        s3Presigner = S3Presigner.builder()
                .credentialsProvider(DefaultCredentialsProvider.create())
                .region(Region.AP_SOUTH_1)
                .build();
    }

    public String uploadFile(String fileName, InputStream inputStream) throws IOException {

        PutObjectRequest fileUploadRequest = PutObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build();
        s3Client.putObject(fileUploadRequest,
                RequestBody.fromInputStream(inputStream, inputStream.available()));
        System.out.println("Uploading file completed : " + fileName);
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket(BUCKET_NAME)
                .key(fileName)
                .build();

        GetObjectPresignRequest presignedRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getRequest)
                .signatureDuration(Duration.ofMinutes(10))
                .build();

        final String preSignedUrl = s3Presigner.presignGetObject(presignedRequest)
                .url()
                .toString();

        return preSignedUrl;
    }

}
