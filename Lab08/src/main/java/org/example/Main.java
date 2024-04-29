package org.example;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        DataImporter.importData("src/books.csv");

        try (Connection conn = DataSource.getConnection()) {
            BookDAO bookDAO = new BookDAO(conn);

            List<Book> books = bookDAO.getAllBooks();
            for (Book book : books) {
                System.out.println(book.getTitle());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}