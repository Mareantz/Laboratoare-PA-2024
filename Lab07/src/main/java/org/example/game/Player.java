package org.example.game;

import java.util.*;

public class Player implements Runnable
{
    private String name;
    private Game game;
    private boolean running = true;
    private List<Token> tiles = new ArrayList<>();
    private List<List<Token>> sequences = new ArrayList<>();
    private Map<Integer, List<Token>> graph = new HashMap<>();

    public Player(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public void run()
    {
        // Extract all tokens first
        while (running)
        {
            synchronized (game.getMonitor())
            {
                while (!game.getCurrentPlayer().equals(this))
                {
                    try
                    {
                        game.getMonitor().wait();
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
                Token tile = game.getBag().extractTile();
                if (tile != null)
                {
                    tiles.add(tile);
                    System.out.println(name + " extracted the token (" + tile.getFirst() + ", " + tile.getSecond() + ")");
                }
                game.nextTurn();
            }
        }

        // After all tokens are extracted, create the graph and find the longest sequence
        createGraph();
        List<Token> longestSequence = findLongestSequence();
        sequences.add(longestSequence);
    }

    public void createGraph()
    {
        for (Token tile : tiles)
        {
            if (!graph.containsKey(tile.getFirst()))
            {
                graph.put(tile.getFirst(), new ArrayList<>());
            }
            graph.get(tile.getFirst()).add(tile);
        }
    }

    public List<Token> findLongestSequence()
    {
        List<Token> longestSequence = new ArrayList<>();
        for (Token tile : tiles)
        {
            List<Token> sequence = new ArrayList<>();
            findLongestSequence(tile, sequence);
            if (sequence.size() > longestSequence.size())
            {
                longestSequence = sequence;
            }
        }
        return longestSequence;
    }

    private void findLongestSequence(Token tile, List<Token> sequence)
    {
        sequence.add(tile);
        List<Token> nextTiles = graph.get(tile.getSecond());
        if (nextTiles != null)
        {
            for (Token nextTile : nextTiles)
            {
                if (!sequence.contains(nextTile))
                {
                    findLongestSequence(nextTile, sequence);
                }
            }
        }
    }

    public List<List<Token>> getSequences()
    {
        return sequences;
    }

    public void stop()
    {
        running = false;
    }

    private void addTileToSequences(Token tile)
    {
        for (List<Token> sequence : sequences)
        {
            Token lastTileInSequence = sequence.get(sequence.size() - 1);
            if (tile.getFirst() == lastTileInSequence.getSecond() + 1)
            {
                sequence.add(tile);
                return;
            }
        }
        // If the tile doesn't fit into any existing sequence, start a new sequence
        List<Token> newSequence = new ArrayList<>();
        newSequence.add(tile);
        sequences.add(newSequence);
    }

    public int getScore()
    {
        List<List<Token>> sequencesCopy = new ArrayList<>(sequences);
        return sequencesCopy.stream().mapToInt(List::size).max().orElse(0);
    }

    public List<Token> getLongestSequence()
    {
        List<Token> longestSequence = null;
        int maxSequenceLength = 0;
        for (List<Token> sequence : sequences)
        {
            if (sequence.size() > maxSequenceLength)
            {
                maxSequenceLength = sequence.size();
                longestSequence = sequence;
            }
        }
        return longestSequence;
    }
}