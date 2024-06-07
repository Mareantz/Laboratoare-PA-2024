package com.smartcity.frontend;

import javax.swing.*;

public class Main
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(() ->
        {
            JFrame frame = new JFrame("SmartCity Parking Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(1024, 768);
            frame.setLocationRelativeTo(null);
            frame.setContentPane(new LoginPanel(frame).getPanel());
            frame.setVisible(true);
        });
    }
}