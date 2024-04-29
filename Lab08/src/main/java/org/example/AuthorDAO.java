package org.example;
import java.sql.*;

public class AuthorDAO {
    private Connection connection;

    public AuthorDAO() {
        try {
            this.connection = DBConnection.getInstance().getConnection();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
