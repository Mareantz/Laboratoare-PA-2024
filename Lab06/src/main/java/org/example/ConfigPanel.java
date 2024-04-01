package org.example;

import javax.swing.*;

public class ConfigPanel extends JPanel {
    final MainFrame frame;
    JLabel gridSizeLabel;
    JTextField gridSizeInput;
    JButton newGameButton;

    public ConfigPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        gridSizeLabel = new JLabel("Grid Size:");
        gridSizeInput = new JTextField(5);
        newGameButton = new JButton("New Game");
        newGameButton.addActionListener(e -> frame.canvas.init(getGridSize(), getGridSize()));

        add(gridSizeLabel);
        add(gridSizeInput);
        add(newGameButton);
    }

    public int getGridSize() {
        try {
            return Integer.parseInt(gridSizeInput.getText());
        } catch (NumberFormatException e) {
            return 10; // default grid size
        }
    }
}