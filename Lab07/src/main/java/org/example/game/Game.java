package org.example.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Game {
    private final Bag bag;
    private final List<Player> players = new ArrayList<>();
    private boolean gameEnded = false;

    private final int numberOfPlayers;
    private final ExecutorService executorService;

    public Game(int numberOfPlayers,int numberOfTokens) {
        this.numberOfPlayers = numberOfPlayers;
        this.bag=new Bag(numberOfTokens);
        this.executorService = Executors.newFixedThreadPool(numberOfPlayers);
    }

    public Bag getBag() {
        return this.bag;
    }

    public void addPlayer(Player player) {
        players.add(player);
        player.setGame(this);
    }

    public void endGame() {
        gameEnded = true;
        for (Player player : players) {
            player.stop();
        }
        executorService.shutdown(); // Stop accepting new tasks
        try {
            // Wait for all tasks to finish
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow(); // Cancel currently executing tasks
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
        }
    }

    public void play() {
        for (Player player : players) {
            executorService.submit(player);
        }

        while (!gameEnded) {
            // Wait for the game to end
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                endGame();
                Thread.currentThread().interrupt();
            }
        }

        for (Player player : players) {
            System.out.println(player.getName() + " score: " + player.getMaxSequenceLength());
        }
    }
}