package org.example.frame;

import org.example.game.GameBoard;
import org.example.game.Node;

import javax.swing.*;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ControlPanel extends JPanel
{
    final MainFrame frame;
    JButton loadButton;
    JButton saveButton;
    JButton exitButton;
    JButton printButton;

    public ControlPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");
        printButton = new JButton("Print");

        saveButton.addActionListener(e ->
        {
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("gameState.ser")))
            {
                out.writeObject(frame.canvas.getGameBoard());
                out.writeBoolean(frame.canvas.getIsRedTurn());
                out.writeObject(frame.canvas.getLastSelectedNode());
                System.out.println("Game state saved to gameState.ser");
            } catch (IOException ex)
            {
                System.out.println("Failed to save game state: " + ex.getMessage());
            }
        });

        loadButton.addActionListener(e ->
        {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("gameState.ser")))
            {
                GameBoard loadedGameBoard = (GameBoard) in.readObject();
                boolean loadedIsRedTurn = in.readBoolean();
                Node loadedLastSelectedNode = (Node) in.readObject();
                frame.canvas.resetGameState();
                frame.canvas.setGameBoard(loadedGameBoard);
                frame.canvas.setIsRedTurn(loadedIsRedTurn);
                frame.canvas.setLastSelectedNode(loadedLastSelectedNode);
                frame.canvas.rows = loadedGameBoard.getRows();
                frame.canvas.cols = loadedGameBoard.getCols();
                frame.canvas.repaint();
                System.out.println("Game state loaded from gameState.ser");
            } catch (IOException | ClassNotFoundException ex)
            {
                System.out.println("Failed to load game state: " + ex.getMessage());
            }
        });
        exitButton.addActionListener(e -> System.exit(0));
        printButton.addActionListener(e ->
        {
            frame.canvas.exportGameBoardImage("gameBoard.png");
        });

        add(loadButton);
        add(saveButton);
        add(exitButton);
        add(printButton);
    }
}