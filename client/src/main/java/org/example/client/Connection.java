package org.example.client;

import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.net.Socket;

public class Connection {
    @Value("${standartConnection.port}")
    private int serverPort;

    @Value("${standartConnection.ip}")
    private String ip;

    public Socket createConnection() throws IOException {
        return new Socket(ip, serverPort);
    }
}
