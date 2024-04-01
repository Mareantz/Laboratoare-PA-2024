package org.example;

import javax.swing.*;
import java.awt.*;

public class DrawingPanel extends JPanel {
    private final MainFrame frame;
    int rows, cols;

    public DrawingPanel(MainFrame frame) {
        this.frame = frame;
    }

    final void init(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int width = getWidth();
        int height = getHeight();

        int cellWidth = width / cols;
        int cellHeight = height / rows;

        g.setColor(Color.BLACK);
        for (int i = 0; i <= cols; i++) {
            g.drawLine(i * cellWidth, 0, i * cellWidth, height);
        }
        for (int i = 0; i <= rows; i++) {
            g.drawLine(0, i * cellHeight, width, i * cellHeight);
        }
    }
}