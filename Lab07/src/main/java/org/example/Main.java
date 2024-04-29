package org.example;

import org.example.game.Game;
import org.example.game.Player;

public class Main
{
    public static void main(String[] args)
    {
        Game game = new Game(7);

        Player player1 = new Player("Marian");
        Player player2 = new Player("Dennis");
        Player player3 = new Player("Cazan");

        game.addPlayer(player1);
        game.addPlayer(player2);
        game.addPlayer(player3);

        game.play(); // Start the game
    }
}