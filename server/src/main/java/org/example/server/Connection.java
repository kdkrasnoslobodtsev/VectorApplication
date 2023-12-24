package org.example.server;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

@Component
public class Connection {
    @Value("${standartConnection.port}")
    private Integer port;

    private final ServerTCP serverTCP;

    private final CommandExecutor commandExecutor;

    public Connection(ServerTCP serverTCP, CommandExecutor commandExecutor) {
        this.serverTCP = serverTCP;
        this.commandExecutor = commandExecutor;
    }

    public void establish() throws IOException {
        ServerSocket serv = new ServerSocket(port);
        String command, response;
        String[] strings;
        while (true) {
            Socket server = serv.accept();
            command = serverTCP.getRequest(server.getInputStream());
            response = commandExecutor.handleCommand(command);
            strings = response.split("\n");
            serverTCP.sendResponse(server.getOutputStream(), strings);
        }
    }
}
