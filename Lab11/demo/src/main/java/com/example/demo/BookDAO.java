package com.example.demo;
import java.sql.*;
import java.util.*;

public class BookDAO {
    private Connection conn;

    public BookDAO(Connection conn) {
        this.conn = conn;
    }

    public List<Book> getAllBooks() throws SQLException {
        List<Book> books = new ArrayList<>();
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM books");

        while (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setTitle(rs.getString("title"));
            book.setAuthors(Arrays.asList(rs.getString("authors").split(",")));
            book.setLanguage(rs.getString("language"));
            book.setPublicationDate(rs.getDate("publication_date"));
            book.setNumberOfPages(rs.getInt("number_of_pages"));
            books.add(book);
        }

        return books;
    }

    public Book getBook(int id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM books WHERE id = ?");
        pstmt.setInt(1, id);
        ResultSet rs = pstmt.executeQuery();

        if (rs.next()) {
            Book book = new Book();
            book.setId(rs.getInt("bookID"));
            book.setTitle(rs.getString("title"));
            book.setAuthors(Arrays.asList(rs.getString("authors").split("/")));
            book.setLanguage(rs.getString("language_code"));
            book.setPublicationDate(rs.getDate("publication_date"));
            book.setNumberOfPages(rs.getInt("num_pages"));
            return book;
        }

        return null;
    }

    public boolean bookExists(Book book) throws SQLException {
        String sql = "SELECT * FROM books WHERE title = ? AND authors = ? AND language = ? AND publication_date = ? AND number_of_pages = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, book.getTitle());
            pstmt.setString(2, String.join(",", book.getAuthors()));
            pstmt.setString(3, book.getLanguage());
            pstmt.setDate(4, new java.sql.Date(book.getPublicationDate().getTime()));
            pstmt.setInt(5, book.getNumberOfPages());

            try (ResultSet rs = pstmt.executeQuery()) {
                return rs.next();
            }
        }
    }

    public boolean insertBook(Book book) throws SQLException {
        if (bookExists(book)) {
            return false;
        }

        String sql = "INSERT INTO books (id, title, authors, language, publication_date, number_of_pages) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, book.getId());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, String.join(",", book.getAuthors()));
            pstmt.setString(4, book.getLanguage());
            pstmt.setDate(5, new java.sql.Date(book.getPublicationDate().getTime()));
            pstmt.setInt(6, book.getNumberOfPages());

            int affectedRows = pstmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        book.setId(generatedKeys.getInt(1));
                    }
                }
            }

            return affectedRows > 0;
        }
    }

    public boolean updateBook(Book book) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("UPDATE books SET title = ?, authors = ?, language = ?, publication_date = ?, number_of_pages = ? WHERE id = ?");
        pstmt.setString(1, book.getTitle());
        pstmt.setString(2, String.join(",", book.getAuthors()));
        pstmt.setString(3, book.getLanguage());
        pstmt.setDate(4, new java.sql.Date(book.getPublicationDate().getTime()));
        pstmt.setInt(5, book.getNumberOfPages());
        pstmt.setInt(6, book.getId());

        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
    }

    public boolean deleteBook(int id) throws SQLException {
        PreparedStatement pstmt = conn.prepareStatement("DELETE FROM books WHERE id = ?");
        pstmt.setInt(1, id);

        int affectedRows = pstmt.executeUpdate();
        return affectedRows > 0;
    }
}