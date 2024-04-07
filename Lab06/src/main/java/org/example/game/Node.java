package org.example.game;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Node implements Serializable
{
    private static final long serialVersionUID = 2L;
    private int row;
    private int col;
    private Stone stone;
    private List<Node> adjacentNodes;

    public Node(int row, int col)
    {
        this.row = row;
        this.col = col;
        this.stone = null;
        this.adjacentNodes = new ArrayList<>();
    }

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Stone getStone()
    {
        return stone;
    }

    public void setStone(Stone stone)
    {
        this.stone = stone;
    }

    public boolean hasStone()
    {
        return stone != null;
    }

    public List<Node> getAdjacentNodes()
    {
        return adjacentNodes;
    }
}