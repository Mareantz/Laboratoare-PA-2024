package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameClient {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public GameClient(String address, int port) throws IOException {
        socket = new Socket(address, port);
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void startClient() throws IOException {
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        String command;
        while ((command = keyboard.readLine()) != null) {
            if ("exit".equals(command)) {
                socket.close();
                break;
            } else {
                out.println(command);
                String response = in.readLine();
                System.out.println(response);
            }
        }
    }
}