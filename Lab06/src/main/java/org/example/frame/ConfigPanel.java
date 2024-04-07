package org.example.frame;

import org.example.game.GameBoard;

import javax.swing.*;


public class ConfigPanel extends JPanel
{
    final MainFrame frame;
    JLabel gridWidthLabel;
    JLabel gridHeightLabel;
    JSpinner gridWidthInput;
    JSpinner gridHeightInput;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame)
    {
        this.frame = frame;
        init();
    }

    private void init()
    {
        gridWidthLabel = new JLabel("Grid Height:");
        gridHeightLabel = new JLabel("Grid Width:");
        gridWidthInput = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
        gridHeightInput = new JSpinner(new SpinnerNumberModel(10, 1, 100, 1));
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e ->
        {
            int rows = getGridWidth();
            int cols = getGridHeight();
            GameBoard newGameBoard = new GameBoard(rows, cols);
            frame.canvas.setGameBoard(newGameBoard);
            frame.canvas.init(rows, cols);
            frame.canvas.resetGameState();
        });

        add(gridWidthLabel);
        add(gridWidthInput);
        add(gridHeightLabel);
        add(gridHeightInput);
        add(newGameButton);
    }

    public int getGridWidth()
    {
        return (int) gridWidthInput.getValue();
    }

    public int getGridHeight()
    {
        return (int) gridHeightInput.getValue();
    }
}