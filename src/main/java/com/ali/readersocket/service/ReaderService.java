package com.ali.readersocket.service;

import com.ali.readersocket.entity.Message;
import com.ali.readersocket.rabbitmq.MessageQueueService;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.sql.Timestamp;

@Component
public class ReaderService {
    private static final Integer port = 8000;

    @Autowired
    private MessageQueueService messageQueueService;

    @Autowired
    private FileService fileService;

    @PostConstruct
    void startReaderService() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Client connected!");

                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    System.out.println("Received: " + line);

                    JSONObject json = new JSONObject(line);

                    int value = json.getInt("value");
                    String hash = json.getString("hash");
                    Timestamp timestamp = new Timestamp(json.getLong("timestamp"));

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(hash).append(", ").append(timestamp).append(", ").append(value).append("\n");

                    if (value > 90) {
                        messageQueueService.sendMessage(line);
                    } else {
                        fileService.appendToFile("message_list.txt", stringBuilder.toString());
                    }
                }
                socket.close();
                System.out.println("Client disconnected.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
