package org.example.game;

public class Token
{
    private final int value1,value2;
    public Token(int value1, int value2)
    {
        this.value1=value1;
        this.value2=value2;
    }

    @Override
    public String toString()
    {
        return "Token{" +
                "value1=" + value1 +
                ", value2=" + value2 +
                '}';
    }
}
