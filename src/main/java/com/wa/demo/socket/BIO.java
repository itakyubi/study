package com.wa.demo.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * BIO
 * 2021-04-08 16:03
 *
 * @author wuao
 */
public class BIO {

    public void serve(int port) throws IOException {
        ServerSocket serverSocket = new ServerSocket(port);
        try {
            while (true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(() -> {
                    try (OutputStream outputStream = clientSocket.getOutputStream()) {
                        outputStream.write("Hello World!".getBytes(StandardCharsets.UTF_8));
                        outputStream.flush();
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
