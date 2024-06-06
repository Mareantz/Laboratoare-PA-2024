package com.smartcity.frontend;

import com.smartcity.frontend.model.ParkingSpaceDTO;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParkingLotLayoutPanel extends JPanel {
    private List<ParkingSpaceDTO> parkingSpaces;
    private Long reservedSpaceId; // ID of the space reserved by the current user

    public ParkingLotLayoutPanel(List<ParkingSpaceDTO> parkingSpaces, Long reservedSpaceId) {
        this.parkingSpaces = parkingSpaces;
        this.reservedSpaceId = reservedSpaceId;
        setLayout(new BorderLayout(10, 10)); // Adjusted layout for better spacing

        JPanel gridPanel = new JPanel(new GridLayout(10, 10, 10, 10)); // Adjust grid layout and spacing

        for (ParkingSpaceDTO space : parkingSpaces) {
            JButton spaceButton = new JButton(String.valueOf(space.getId()));
            spaceButton.setPreferredSize(new Dimension(50, 50));
            if (space.isReserved()) {
                if (space.getId().equals(reservedSpaceId)) {
                    spaceButton.setBackground(Color.YELLOW); // User's reserved space
                } else {
                    spaceButton.setBackground(Color.RED); // Other reserved spaces
                }
            } else {
                spaceButton.setBackground(Color.GREEN); // Free spaces
            }
            gridPanel.add(spaceButton);
        }

        // Adding an entrance label at the bottom
        JLabel entranceLabel = new JLabel("Entrance", JLabel.CENTER);
        entranceLabel.setFont(new Font("Arial", Font.BOLD, 16));

        add(gridPanel, BorderLayout.CENTER);
        add(entranceLabel, BorderLayout.SOUTH);
    }
}
