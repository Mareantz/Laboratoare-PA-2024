package org.example.game;

public class Timekeeper implements Runnable
{
    private final Game game;
    private final long timeLimit;

    public Timekeeper(Game game, long timeLimit)
    {
        this.game = game;
        this.timeLimit = timeLimit;
    }

    @Override
    public void run()
    {
        long startTime = System.currentTimeMillis();
        while (true)
        {
            long currentTime = System.currentTimeMillis();
            long elapsedTime = currentTime - startTime;
            if (elapsedTime > timeLimit)
            {
                game.stopGame();
                break;
            }
            try
            {
                Thread.sleep(1000); // Sleep for a while before checking again
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
    }
}