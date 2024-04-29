package org.example.game;

public class Token
{
    private final int first, second;

    public Token(int first, int second)
    {
        this.first = first;
        this.second = second;
    }

    public int getFirst()
    {
        return first;
    }

    public int getSecond()
    {
        return second;
    }

    @Override
    public String toString()
    {
        return "Token{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }
}
