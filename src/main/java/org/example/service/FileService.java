package org.example.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.example.service.exception.InvalidFileException;
import org.hibernate.boot.model.relational.Loggable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FileService {
    @Autowired
    AmazonS3 s3Client;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private String bucket_name = "ascending-project-s3-bucket";

    public void uploadFile(File file) {
        s3Client.putObject(bucket_name, file.getName(), file);
    }

    public String upload(MultipartFile file) throws IOException {
        if(file == null) {
            logger.error("Cannot upload empty file");
            throw new InvalidFileException("Unable to upload empty file to bucket.");
        }
        PutObjectRequest request = new PutObjectRequest(bucket_name,
                file.getOriginalFilename(), file.getInputStream(), null);
        s3Client.putObject(request);

        return s3Client.getUrl(bucket_name, file.getOriginalFilename()).toString();
    }
}
