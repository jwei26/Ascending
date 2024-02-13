package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Hello world!
 *
 */
@SpringBootApplication(scanBasePackages = {"org.example"})
@ServletComponentScan(basePackages = "org.example.filter")
public class ApplicationBootStrap extends SpringBootServletInitializer
{
    public static void main( String[] args )
    {
        SpringApplication.run(ApplicationBootStrap.class, args);
    }
}
