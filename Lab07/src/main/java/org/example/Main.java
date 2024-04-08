package org.example;

import org.example.game.Bag;
import org.example.game.Player;
import org.example.game.Game;

public class Main
{
    public static void main(String[] args)
    {
        System.out.println("Hello world!");
        Bag bag1=new Bag(30);
        System.out.println(bag1);

        Game game1;
        game1 = new Game(3,30);
        game1.addPlayer(new Player("Player 1"));
        game1.addPlayer(new Player("Player 2"));
        game1.addPlayer(new Player("Player 3"));
        game1.play();
    }
}