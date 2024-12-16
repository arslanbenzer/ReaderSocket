package com.ali.readersocket.service;

import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class FileService {

    public void appendToFile(String fileName, String content) throws IOException {
        FileOutputStream fos = new FileOutputStream(fileName, true);
        fos.write(content.getBytes());
        fos.close();
    }
}
