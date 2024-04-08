package org.example.game;

import java.util.List;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class Player implements Runnable
{
    private String name;
    private Game game;
    private AtomicBoolean running;
    private List<Token> tokens = new ArrayList<>();
    private int maxSequenceLength = 0;

    public Player(String name)
    {
        this.name = name;
        this.running = new AtomicBoolean(true);
    }

    public String getName()
    {
        return name;
    }

    public void setGame(Game game)
    {
        this.game = game;
    }

    public synchronized void stop()
    {
        this.running.set(false);
    }

    public int getMaxSequenceLength()
    {
        return this.maxSequenceLength;
    }

    private void createSequence() {
    // Sort the tokens based on the first number
    tokens.sort((t1, t2) -> Integer.compare(t1.getFirstNumber(), t2.getFirstNumber()));

    // List to store the current sequence
    List<Token> currentSequence = new ArrayList<>();

    // Iterate over the tokens
    for (Token token : tokens) {
        // If the current sequence is empty or the last token in the sequence
        // has the same second number as the first number of the current token,
        // add the current token to the sequence
        if (currentSequence.isEmpty() ||
            currentSequence.get(currentSequence.size() - 1).getSecondNumber() == token.getFirstNumber()) {
            currentSequence.add(token);
        } else {
            // If the current token cannot be added to the sequence, start a new sequence
            currentSequence = new ArrayList<>();
            currentSequence.add(token);
        }

        // Update the max sequence length
        maxSequenceLength = Math.max(maxSequenceLength, currentSequence.size());
    }
}

    public synchronized void run()
    {
        if (game == null || game.getBag() == null) {
            throw new IllegalStateException("Game or bag is null");
        }

        while (running.get())
        {
            List<Token> extractedTokens = game.getBag().extractTokens(1);
            if (extractedTokens.isEmpty())
            {
                stop();
                break;
            }
            tokens.addAll(extractedTokens);
            createSequence();
            if (maxSequenceLength == game.getBag().getNumberOfTokens())
            {
                game.endGame();
                break;
            }
        }
    }
}