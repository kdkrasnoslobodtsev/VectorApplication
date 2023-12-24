package org.example.client;

import org.springframework.stereotype.Service;

import java.io.*;

@Service
public class ClientTCP {
    public Boolean getResponse(InputStream is) throws IOException {
        Boolean exit = false;
        BufferedReader fromServer;
        fromServer = new BufferedReader(new InputStreamReader(is));
        String response = fromServer.readLine();
        int length = Integer.parseInt(response);
        for (int i = 0; i < length; ++i) {
            response = fromServer.readLine();
            if (response.equals("Server response: Exit")) {
                exit = true;
            }
            System.out.println(response);
        }
        fromServer.close();
        return exit;
    }

    public void sendRequest(OutputStream os, String request) throws IOException {
        PrintWriter toServer = new PrintWriter(os, true);
        toServer.println(request);
    }
}
