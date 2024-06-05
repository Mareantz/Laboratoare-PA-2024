package com.smartcity.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcity.frontend.model.ParkingLotDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class UserPanel {
    private JPanel panel;
    private JTable parkingLotsTable;
    private JButton reserveButton;
    private JButton logoutButton;
    private JLabel welcomeLabel; // Add welcome label
    private Long userId;
    private String username;

    public UserPanel(Long userId, String username) {
        this.userId = userId;
        this.username = username;
        initializeComponents();
    }

    private void initializeComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        parkingLotsTable = new JTable();
        reserveButton = new JButton("Reserve");
        logoutButton = new JButton("Logout");
        welcomeLabel = new JLabel("Welcome, " + username); // Initialize welcome label

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.WEST); // Add welcome label to top panel

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(reserveButton);
        buttonPanel.add(logoutButton);

        panel.add(topPanel, BorderLayout.NORTH); // Add top panel to main panel
        panel.add(new JScrollPane(parkingLotsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = parkingLotsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Long parkingLotId = ((ParkingLotTableModel) parkingLotsTable.getModel()).getParkingLotId(selectedRow); // Get ID from model
                    int response = JOptionPane.showConfirmDialog(panel, "Are you sure you want to reserve this parking lot?", "Confirm Reservation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            makeReservation(parkingLotId);
                        } catch (IOException ioException) {
                            ioException.printStackTrace();
                            JOptionPane.showMessageDialog(panel, "Failed to create reservation");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a parking lot to reserve");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle logout logic (e.g., navigate to login panel)
                JOptionPane.showMessageDialog(panel, "Logged out successfully");
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                topFrame.setContentPane(new LoginPanel(topFrame).getPanel());
                topFrame.revalidate();
            }
        });

        try {
            loadParkingLots();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Failed to load parking lots");
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    private void loadParkingLots() throws IOException {
        String url = "http://localhost:8081/api/parking-lots";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = client.execute(get)) {
                String responseString = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                List<ParkingLotDTO> parkingLots = mapper.readValue(responseString,
                        mapper.getTypeFactory().constructCollectionType(List.class, ParkingLotDTO.class));
                parkingLotsTable.setModel(new ParkingLotTableModel(parkingLots));
            }
        }
    }

    private void makeReservation(Long parkingLotId) throws IOException {
        String url = "http://localhost:8081/api/reservations";
        String json = "{\"userId\":" + userId + ",\"parkingLotId\":" + parkingLotId + "}";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(post)) {
                String responseString = EntityUtils.toString(response.getEntity());
                if (responseString.contains("Reservation created successfully")) {
                    JOptionPane.showMessageDialog(panel, "Reservation created successfully");
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to create reservation: " + responseString);
                }
            }
        }
    }

    class ParkingLotTableModel extends AbstractTableModel {
        private final String[] columnNames = {"Name", "Address", "Capacity", "Available Spaces"};
        private final List<ParkingLotDTO> parkingLots;

        public ParkingLotTableModel(List<ParkingLotDTO> parkingLots) {
            this.parkingLots = parkingLots;
        }

        @Override
        public int getRowCount() {
            return parkingLots.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            ParkingLotDTO parkingLot = parkingLots.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return parkingLot.getName();
                case 1:
                    return parkingLot.getAddress();
                case 2:
                    return parkingLot.getCapacity();
                case 3:
                    return parkingLot.getAvailableSpaces();
                default:
                    return null;
            }
        }

        public Long getParkingLotId(int rowIndex) {
            return parkingLots.get(rowIndex).getId();
        }
    }
}
