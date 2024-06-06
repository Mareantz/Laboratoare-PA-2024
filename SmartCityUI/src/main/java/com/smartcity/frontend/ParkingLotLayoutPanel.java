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
        setLayout(new GridLayout(5, 5, 5, 5)); // Adjust grid layout as needed

        for (ParkingSpaceDTO space : parkingSpaces) {
            JButton spaceButton = new JButton();
            spaceButton.setPreferredSize(new Dimension(50, 50));
            if (space.isReserved()) {
                if (space.getId().equals(reservedSpaceId)) {
                    spaceButton.setBackground(Color.BLUE); // User's reserved space
                } else {
                    spaceButton.setBackground(Color.RED); // Other reserved spaces
                }
            } else {
                spaceButton.setBackground(Color.GREEN); // Free spaces
            }
            add(spaceButton);
        }
    }
}
