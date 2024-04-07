package org.example.game;

public class Stick {
    private Node startNode;
    private Node endNode;

    public Stick(Node startNode, Node endNode) {
        this.startNode = startNode;
        this.endNode = endNode;
    }

    public Node getStartNode() {
        return startNode;
    }

    public Node getEndNode() {
        return endNode;
    }
}