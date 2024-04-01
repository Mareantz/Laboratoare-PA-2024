package org.example;

import javax.swing.*;

public class ControlPanel extends JPanel {
    final MainFrame frame;
    JButton loadButton;
    JButton saveButton;
    JButton exitButton;

    public ControlPanel(MainFrame frame) {
        this.frame = frame;
        init();
    }

    private void init() {
        loadButton = new JButton("Load");
        saveButton = new JButton("Save");
        exitButton = new JButton("Exit");

        loadButton.addActionListener(e -> {
            // TODO: Implement game state loading
        });
        saveButton.addActionListener(e -> {
            // TODO: Implement game state saving
        });
        exitButton.addActionListener(e -> System.exit(0));

        add(loadButton);
        add(saveButton);
        add(exitButton);
    }
}