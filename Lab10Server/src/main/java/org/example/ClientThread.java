package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread {
    private Socket socket;
    private PrintWriter out;
    private BufferedReader in;

    public ClientThread(Socket socket) throws IOException {
        this.socket = socket;
        out = new PrintWriter(socket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public void run() {
        try {
            String request;
            while ((request = in.readLine()) != null) {
                if ("stop".equals(request)) {
                    out.println("Server stopped");
                    socket.close();
                    break;
                } else {
                    out.println("Server received the request " + request);
                }
            }
        } catch (SocketException e) {
            System.out.println("Client disconnected: " + socket.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}