package com.smartcity.frontend;

import com.smartcity.frontend.model.ParkingSpaceDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParkingLotLayoutPanel extends JPanel
{
    public ParkingLotLayoutPanel(List<ParkingSpaceDTO> parkingSpaces, Long reservedSpaceId)
    {
        setLayout(new BorderLayout(10, 10));
        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 10, 10));

        for (ParkingSpaceDTO space : parkingSpaces)
        {
            JButton spaceButton = new JButton(String.valueOf(space.getId()));
            spaceButton.setPreferredSize(new Dimension(50, 50));
            if (space.isReserved())
            {
                if (space.getId().equals(reservedSpaceId))
                {
                    spaceButton.setBackground(Color.YELLOW);
                }
                else
                {
                    spaceButton.setBackground(Color.RED);
                }
            }
            else
            {
                spaceButton.setBackground(Color.GREEN);
            }
            gridPanel.add(spaceButton);
        }

        JLabel entranceLabel = new JLabel("Entrance", JLabel.CENTER);
        entranceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(gridPanel, BorderLayout.CENTER);
        add(entranceLabel, BorderLayout.SOUTH);
    }
}