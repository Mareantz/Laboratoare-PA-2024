package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameServer gameServer = new GameServer(2308);
            gameServer.startServer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}