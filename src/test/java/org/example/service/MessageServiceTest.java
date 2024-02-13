package org.example.service;


import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.example.ApplicationBootStrap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootStrap.class)
public class MessageServiceTest {
    @Autowired
    MessageService messageService;
    @Autowired
    private AmazonSQS mockSqs;
    @Mock
    GetQueueUrlResult mockGetQueueUrlResult;

    @Test
    public void sendMessageTest() {
        when(mockSqs.getQueueUrl(anyString())).thenReturn(mockGetQueueUrlResult);
        when(mockGetQueueUrlResult.getQueueUrl()).thenReturn(anyString());
        messageService.sendMessage("TestQueue", "TestMessage", 0);
        verify(mockSqs, times(1)).sendMessage(any(SendMessageRequest.class));
    }
}