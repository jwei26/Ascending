package org.example.config;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.sqs.AmazonSQS;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import static org.mockito.Mockito.mock;

@Configuration
@Profile("test")
public class AWSTestconfig {
    private Regions defaultRegion = Regions.US_EAST_1;
    @Bean
    public AmazonS3 getAmazonS3() {
        return mock(AmazonS3.class);
    }

    @Bean
    public AmazonSQS getAmazonSQS() {
        return mock(AmazonSQS.class);
    }
}