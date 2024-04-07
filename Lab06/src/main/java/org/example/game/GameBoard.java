package org.example.game;

import java.util.Random;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class GameBoard implements Serializable {
    private static final long serialVersionUID = 1L;
    private Node[][] nodes;
    private transient Random random;

    public Node[][] getNodes() {
        return nodes;
    }

    public GameBoard(int rows, int cols) {
        nodes = new Node[rows][cols];
        random = new Random();
        initializeNodes();
        initializeEdges();
    }

    private void initializeNodes() {
        for (int row = 0; row < nodes.length; row++) {
            for (int col = 0; col < nodes[row].length; col++) {
                nodes[row][col] = new Node(row, col);
            }
        }
    }

    public void setNodes(Node[][] nodes) {
        this.nodes = nodes;
    }

    public int getRows() {
        return nodes.length;
    }

    public int getCols() {
        return nodes[0].length;
    }

    private void initializeEdges() {
        for (int i = 0; i < nodes.length; i++) {
            for (int j = 0; j < nodes[i].length; j++) {
                if (i > 0 && random.nextBoolean()) {
                    nodes[i][j].getAdjacentNodes().add(nodes[i - 1][j]);
                    nodes[i - 1][j].getAdjacentNodes().add(nodes[i][j]);
                }
                if (j > 0 && random.nextBoolean()) {
                    nodes[i][j].getAdjacentNodes().add(nodes[i][j - 1]);
                    nodes[i][j - 1].getAdjacentNodes().add(nodes[i][j]);
                }
            }
        }
    }

}