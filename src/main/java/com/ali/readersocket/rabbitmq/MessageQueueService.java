package com.ali.readersocket.rabbitmq;

import com.rabbitmq.client.Channel;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MessageQueueService
{
    public void sendMessage(String message) throws IOException, TimeoutException {
        Factory factory = new Factory();
        Channel channel = factory.init();
        channel.basicPublish("", "message_queue", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
