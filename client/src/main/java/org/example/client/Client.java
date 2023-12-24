package org.example.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.net.Socket;
import java.util.Scanner;

@Component
public class Client {
    private final Connection connection;
    private final ClientTCP clientTCP;

    public Client(@Qualifier("standartConnection") Connection connection, ClientTCP clientTCP) {
        this.connection = connection;
        this.clientTCP = clientTCP;
    }

    public void start() {
        try {
            Scanner sc = new Scanner(System.in);
            String command;
            Boolean exit = false;
            do {
                Socket sock = connection.createConnection();
                command = sc.nextLine();
                clientTCP.sendRequest(sock.getOutputStream(), command);
                exit = clientTCP.getResponse(sock.getInputStream());
            } while (!exit);
            System.out.print("Client finished working with server.");
        } catch(Exception e) {
            System.err.println("Server is unavailable.");
        }
    }
}
