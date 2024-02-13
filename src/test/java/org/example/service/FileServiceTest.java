package org.example.service;

import com.amazonaws.services.s3.AmazonS3;
import org.example.ApplicationBootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class FileServiceTest {
    @Autowired
    FileService fileService;
    @Mock
    private MultipartFile mockfile;
    @Autowired
    private AmazonS3 amazonS3Client;

    @Test
    public void uploadTest() throws IOException {
        when(mockfile.getName()).thenReturn("testFile");

        fileService.upload(mockfile);
        verify(amazonS3Client, times(1)).putObject(anyString(), anyString(), (File)any(File.class));
    }
}
