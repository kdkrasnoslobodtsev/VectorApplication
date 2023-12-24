package org.example.server;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class Server {
    private final Connection connection;

    public Server(Connection connection) {
        this.connection = connection;
    }

    public void start() {
        try {
            connection.establish();
        } catch (IOException e) {
            System.err.println("Server cannot be turned on.");
        }
    }
}
