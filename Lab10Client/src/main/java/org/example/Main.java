package org.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            GameClient gameClient = new GameClient("localhost", 2308);
            gameClient.startClient();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}