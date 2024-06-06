package com.smartcity.frontend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.smartcity.frontend.model.ParkingLotDTO;
import com.smartcity.frontend.model.ParkingLotDetailDTO;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class UserPanel {
    private JPanel panel;
    private JTable parkingLotsTable;
    private JButton reserveButton;
    private JButton viewLayoutButton;
    private JButton logoutButton;
    private JButton extendReservationButton;
    private JButton cancelReservationButton;
    private JLabel welcomeLabel;
    private Long userId;
    private String username;
    private List<ParkingLotDTO> parkingLots;
    private Long activeReservationId;
    private Long activeParkingSpaceId;
    private Timer refreshTimer;

    public UserPanel(Long userId, String username) {
        this.userId = userId;
        this.username = username;
        initializeComponents();
        startRefreshTimer();
    }

    private void initializeComponents() {
        panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        parkingLotsTable = new JTable();
        reserveButton = new JButton("Reserve");
        viewLayoutButton = new JButton("View Layout");
        logoutButton = new JButton("Logout");
        extendReservationButton = new JButton("Extend Reservation");
        cancelReservationButton = new JButton("Cancel Reservation");
        welcomeLabel = new JLabel("Welcome, " + username);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(welcomeLabel, BorderLayout.WEST);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(reserveButton);
        buttonPanel.add(viewLayoutButton);
        buttonPanel.add(logoutButton);
        buttonPanel.add(extendReservationButton);
        buttonPanel.add(cancelReservationButton);
        extendReservationButton.setVisible(false);
        cancelReservationButton.setVisible(false);


        panel.add(topPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(parkingLotsTable), BorderLayout.CENTER);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        reserveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = parkingLotsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Long parkingLotId = ((ParkingLotTableModel) parkingLotsTable.getModel()).getParkingLotId(selectedRow);
                    int response = JOptionPane.showConfirmDialog(panel, "Are you sure you want to reserve this parking lot?", "Confirm Reservation", JOptionPane.YES_NO_OPTION);
                    if (response == JOptionPane.YES_OPTION) {
                        try {
                            makeReservation(parkingLotId);
                            loadParkingLotsAndReservations();
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

        viewLayoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = parkingLotsTable.getSelectedRow();
                if (selectedRow >= 0) {
                    Long parkingLotId = ((ParkingLotTableModel) parkingLotsTable.getModel()).getParkingLotId(selectedRow);
                    try {
                        viewParkingLotLayout(parkingLotId);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                        JOptionPane.showMessageDialog(panel, "Failed to load parking lot layout");
                    }
                } else {
                    JOptionPane.showMessageDialog(panel, "Please select a parking lot to view its layout");
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(panel, "Logged out successfully");
                JFrame topFrame = (JFrame) SwingUtilities.getWindowAncestor(panel);
                topFrame.setContentPane(new LoginPanel(topFrame).getPanel());
                topFrame.revalidate();
            }
        });

        extendReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showExtendOptions();
            }
        });

        cancelReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    cancelReservation();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Failed to cancel reservation");
                }
            }
        });

        try {
            loadParkingLotsAndReservations();
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(panel, "Failed to load parking lots");
        }
    }

    public JPanel getPanel() {
        return panel;
    }

    private void loadParkingLotsAndReservations() throws IOException {
        String parkingLotsUrl = "http://localhost:8081/api/parking-lots";
        String reservationsUrl = "http://localhost:8081/api/reservations";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            // Load parking lots
            HttpGet getParkingLots = new HttpGet(parkingLotsUrl);
            getParkingLots.setHeader("Content-type", "application/json");
            try (CloseableHttpResponse parkingLotsResponse = client.execute(getParkingLots)) {
                String parkingLotsResponseString = EntityUtils.toString(parkingLotsResponse.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                parkingLots = mapper.readValue(parkingLotsResponseString, mapper.getTypeFactory().constructCollectionType(List.class, ParkingLotDTO.class));
                parkingLotsTable.setModel(new ParkingLotTableModel(parkingLots));
            }

            // Load reservations
            HttpGet getReservations = new HttpGet(reservationsUrl);
            getReservations.setHeader("Content-type", "application/json");
            try (CloseableHttpResponse reservationsResponse = client.execute(getReservations)) {
                String reservationsResponseString = EntityUtils.toString(reservationsResponse.getEntity());
                updateReservationsTable(reservationsResponseString);
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

    private void cancelReservation() throws IOException {
        String url = "http://localhost:8081/api/reservations/" + activeReservationId;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpDelete delete = new HttpDelete(url);
            delete.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = client.execute(delete)) {
                String responseString = EntityUtils.toString(response.getEntity());
                if (responseString.contains("Reservation canceled successfully")) {
                    JOptionPane.showMessageDialog(panel, "Reservation canceled successfully");
                    loadParkingLotsAndReservations(); // Refresh the tables
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to cancel reservation: " + responseString);
                }
            }
        }
    }


    private void viewParkingLotLayout(Long parkingLotId) throws IOException {
        String url = "http://localhost:8081/api/parking-lots/" + parkingLotId;

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet get = new HttpGet(url);
            get.setHeader("Content-type", "application/json");

            try (CloseableHttpResponse response = client.execute(get)) {
                String responseString = EntityUtils.toString(response.getEntity());
                ObjectMapper mapper = new ObjectMapper();
                ParkingLotDetailDTO parkingLotDetail = mapper.readValue(responseString, ParkingLotDetailDTO.class);

                JFrame layoutFrame = new JFrame("Parking Lot Layout - " + parkingLotDetail.getName());
                layoutFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                layoutFrame.setSize(1024, 768);

                Long reservedSpaceId = activeParkingSpaceId;
                System.out.println("Reserved Space ID: " + reservedSpaceId);

                ParkingLotLayoutPanel layoutPanel = new ParkingLotLayoutPanel(parkingLotDetail.getParkingSpaces(), reservedSpaceId);

                layoutFrame.setLocationRelativeTo(null);
                layoutFrame.setContentPane(layoutPanel);
                layoutFrame.setVisible(true);
            }
        }
    }

    private void showExtendOptions() {
        String[] options = {"30 minutes", "1 hour"};
        int choice = JOptionPane.showOptionDialog(panel, "Extend reservation by:", "Extend Reservation",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (choice == 0) {
            try {
                extendReservation(30);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        } else if (choice == 1) {
            try {
                extendReservation(60);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void extendReservation(int minutes) throws IOException {
        String url = "http://localhost:8081/api/reservations/extend";
        String json = "{\"reservationId\":" + activeReservationId + ",\"minutes\":" + minutes + "}";

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpPost post = new HttpPost(url);
            post.setHeader("Content-type", "application/json");
            post.setEntity(new StringEntity(json));

            try (CloseableHttpResponse response = client.execute(post)) {
                String responseString = EntityUtils.toString(response.getEntity());
                if (responseString.contains("Reservation extended successfully")) {
                    JOptionPane.showMessageDialog(panel, "Reservation extended successfully");
                    loadParkingLotsAndReservations(); // Refresh the tables
                } else {
                    JOptionPane.showMessageDialog(panel, "Failed to extend reservation: " + responseString);
                }
            }
        }
    }

    private void updateReservationsTable(String jsonResponse) {
        try {
            JSONArray reservations = new JSONArray(jsonResponse);
            String[] columnNames = {"Reservation ID", "User ID", "Parking Lot ID", "Parking Space ID", "Start Time", "End Time"};
            DefaultTableModel model = new DefaultTableModel(columnNames, 0);
            activeReservationId = null;
            activeParkingSpaceId = null;

            for (int i = 0; i < reservations.length(); i++) {
                JSONObject reservation = reservations.getJSONObject(i);
                Long reservationId = reservation.getLong("reservationId");
                Long userId = reservation.has("userId") ? reservation.getLong("userId") : null;
                Long parkingLotId = reservation.has("parkingLotId") ? reservation.getLong("parkingLotId") : null;
                Long parkingSpaceId = reservation.has("parkingSpaceId") ? reservation.getLong("parkingSpaceId") : null;
                String startTime = reservation.getString("startTime");
                String endTime = reservation.getString("endTime");

                Object[] row = {reservationId, userId, parkingLotId, parkingSpaceId, startTime, endTime};
                model.addRow(row);

                if (userId != null && userId.equals(this.userId) && LocalDateTime.parse(endTime).isAfter(LocalDateTime.now())) {
                    activeReservationId = reservationId;
                    activeParkingSpaceId = parkingSpaceId;
                }
            }

            updateExtendButtonVisibility();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startRefreshTimer() {
        int delay = 10000; // 10 seconds delay
        refreshTimer = new Timer(delay, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    loadParkingLotsAndReservations(); // Periodically refresh the data
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
        refreshTimer.start();
    }

    private void updateExtendButtonVisibility() {
        if (activeReservationId != null) {
            extendReservationButton.setVisible(true);
            cancelReservationButton.setVisible(true);
        } else {
            extendReservationButton.setVisible(false);
            cancelReservationButton.setVisible(false);
        }
        panel.revalidate();
        panel.repaint();
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
