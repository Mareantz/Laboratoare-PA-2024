package org.example.game;

import java.util.*;

public class Bag
{
    private final Queue<Token> tiles;

    public Bag(int n)
    {
        this.tiles = new LinkedList<>();
        for (int i = 1; i <= n; i++)
        {
            for (int j = 1; j <= n; j++)
            {
                if (i != j)
                {
                    tiles.add(new Token(i, j));
                }
            }
        }
        Collections.shuffle((List<?>) tiles);
    }

    public synchronized Token extractTile()
    {
        if (tiles.isEmpty())
        {
            return null;
        }
        return tiles.poll();
    }

    public boolean isEmpty()
    {
        return tiles.isEmpty();
    }
}