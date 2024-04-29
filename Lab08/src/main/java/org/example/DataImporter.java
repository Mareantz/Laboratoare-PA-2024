package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.sql.SQLException;

public class DataImporter {
    public static List<Book> importData(String filePath) {
        List<Book> books = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            String[] lineInArray;
            reader.skip(1);

            try {
                BookDAO bookDAO = new BookDAO(DataSource.getConnection());

                while ((lineInArray = reader.readNext()) != null) {
                    System.out.println(Arrays.toString(lineInArray));
                    Book book = new Book();
                    book.setId(Integer.parseInt(lineInArray[0].trim()));
                    book.setTitle(lineInArray[1].trim());
                    book.setAuthors(Arrays.asList(lineInArray[2].trim().split("/")));
                    book.setLanguage(lineInArray[3].trim());
                    book.setNumberOfPages(Integer.parseInt(lineInArray[4].trim()));
                    book.setPublicationDate(new SimpleDateFormat("MM/dd/yyyy").parse(lineInArray[5].trim()));
                    try {
                        bookDAO.insertBook(book);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (IOException | CsvException | ParseException e) {
            e.printStackTrace();
        }

        return books;
    }
}