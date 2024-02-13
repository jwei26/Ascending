package org.example.controller;

import org.example.service.FileService;
import org.example.service.exception.InvalidFileException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
@RequestMapping(value = "/file")
public class FileController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    FileService fileService;

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            String url = fileService.upload(file);
            logger.error("Successful upload file {} to S3", file.getOriginalFilename());
            return ResponseEntity.ok().body(url);
        } catch (InvalidFileException e) {
            logger.error("File type not support");
            return ResponseEntity.badRequest().build();
        } catch (IOException e) {
            logger.error("Unable to upload file to AWS S3");
            return ResponseEntity.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).build();
        }
    }
}
