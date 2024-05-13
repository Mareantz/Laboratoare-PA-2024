package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class GameServer {
    private ServerSocket serverSocket;
    private boolean running = false;

    public GameServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        running = true;
    }

    public void startServer() {
        while (running) {
            try {
                System.out.println("Waiting for a client ...");
                Socket socket = serverSocket.accept();
                System.out.println("Client connected: " + socket.getInetAddress());
                new ClientThread(socket).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void stopServer() throws IOException {
        this.running = false;
        if (serverSocket != null) {
            serverSocket.close();
        }
    }
}