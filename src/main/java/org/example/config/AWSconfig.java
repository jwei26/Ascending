package org.example.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


@Configuration
@Profile("dev")
public class AWSconfig {
    private Regions defaultRegion = Regions.US_EAST_1;
    @Bean
    public AmazonS3 getAmazonS3() {
        return AmazonS3ClientBuilder.standard().withRegion(defaultRegion).build();
    }
    @Bean
    public AmazonSQS getAmazonSQS() {
        return AmazonSQSClientBuilder.standard().withRegion(defaultRegion).build();
    }
}