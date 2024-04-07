package org.example.game;

import java.awt.Color;
import java.io.Serializable;

public class Stone implements Serializable
{
    private static final long serialVersionUID = 3L;
    private Node node;
    private Color color;

    public Stone(Node node, Color color)
    {
        this.node = node;
        this.color = color;
    }

    public Color getColor()
    {
        return color;
    }
}