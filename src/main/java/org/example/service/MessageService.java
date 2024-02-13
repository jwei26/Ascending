package org.example.service;

import com.amazonaws.services.mediaconvert.model.GetQueueResult;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClientBuilder;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageService {
    @Autowired
    AmazonSQS sqs;
    public void sendMessage() {
        sqs.createQueue("Test Queue" + System.currentTimeMillis());
        String queue_url = sqs.getQueueUrl("Test Queue").getQueueUrl();
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody("hello world")
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }

    public void sendMessage(String queueName, String messageBody, int delaySeconds) {
        String queue_url = getQueueUrl(queueName);
        SendMessageRequest send_msg_request = new SendMessageRequest()
                .withQueueUrl(queue_url)
                .withMessageBody("hello world")
                .withDelaySeconds(5);
        sqs.sendMessage(send_msg_request);
    }

    public String getQueueUrl(String queueName) {
        //sqs.createQueue("Test Queue" + System.currentTimeMillis());
        GetQueueUrlResult getQueueResult = sqs.getQueueUrl(queueName);
        return getQueueResult.getQueueUrl();
    }


}
