package com.ali.readersocket.rabbitmq;

import com.rabbitmq.client.Channel;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class MessageQueueService {

    private Channel channel;

    @PostConstruct
    public void init() throws IOException, TimeoutException {
        Factory factory = new Factory();
        this.channel = factory.init();
    }

    public void sendMessage(String message) throws IOException, TimeoutException {
        channel.basicPublish("", "message_queue", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
    }
}
