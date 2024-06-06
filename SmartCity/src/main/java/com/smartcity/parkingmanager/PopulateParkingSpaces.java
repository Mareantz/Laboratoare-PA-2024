package com.smartcity.parkingmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PopulateParkingSpaces {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/parking_manager";
    private static final String USER = "root";
    private static final String PASS = "root";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Retrieve all parking lots
            String selectParkingLotsSQL = "SELECT parking_lot_id, capacity FROM parking_lots";
            try (PreparedStatement selectStmt = conn.prepareStatement(selectParkingLotsSQL);
                 ResultSet rs = selectStmt.executeQuery()) {
                // Prepare the insert statement for parking spaces
                String insertParkingSpaceSQL = "INSERT INTO parking_spaces (space_number, parking_lot_id, is_reserved) VALUES (?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertParkingSpaceSQL)) {
                    // Prepare the count statement for existing parking spaces
                    String countParkingSpacesSQL = "SELECT COUNT(*) FROM parking_spaces WHERE parking_lot_id = ?";
                    try (PreparedStatement countStmt = conn.prepareStatement(countParkingSpacesSQL)) {
                        // Iterate through each parking lot
                        while (rs.next()) {
                            long parkingLotId = rs.getLong("parking_lot_id");
                            int capacity = rs.getInt("capacity");

                            // Count existing parking spaces for the parking lot
                            countStmt.setLong(1, parkingLotId);
                            try (ResultSet countRs = countStmt.executeQuery()) {
                                if (countRs.next()) {
                                    int existingSpaces = countRs.getInt(1);

                                    // Insert parking spaces only if there are less than capacity
                                    for (int i = existingSpaces + 1; i <= capacity; i++) {
                                        insertStmt.setString(1, "Space " + i);
                                        insertStmt.setLong(2, parkingLotId);
                                        insertStmt.setBoolean(3, false); // Default value for is_reserved
                                        insertStmt.executeUpdate();
                                    }
                                }
                            }
                        }
                    }
                }
            }
            System.out.println("Parking spaces populated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
