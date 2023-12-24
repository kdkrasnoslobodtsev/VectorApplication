package org.example.server;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ServerTCP {
    public String getRequest(InputStream is) throws IOException {
        BufferedReader fromClient = new BufferedReader(new InputStreamReader(is));
        String command = fromClient.readLine();
        return command;
    }

    public void sendResponse(OutputStream os, String[] response) throws IOException {
        PrintWriter toClient = new PrintWriter(os, true);
        toClient.println(response.length);
        for (int i = 0; i < response.length; ++i) {
            if (i == 0) {
                toClient.println("Server response: " + response[i]);
            } else {
                toClient.println(response[i]);
            }
        }
    }
}
