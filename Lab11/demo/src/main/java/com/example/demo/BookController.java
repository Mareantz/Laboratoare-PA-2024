package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@RestController
public class BookController {

    private final BookDAO bookDAO;

    public BookController() throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/bookstore", "root", "");
        this.bookDAO = new BookDAO(connection);
    }

    @GetMapping("/books")
    public List<Book> getAllBooks() {
        try {
            return bookDAO.getAllBooks();
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving books", e);
        }
    }
}