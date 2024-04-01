package org.example;

import org.example.shell.ExportCommand;
import org.example.shell.ReportCommand;
import org.example.shell.ViewCommand;
import org.example.shell.Shell;
import org.example.model.Document;
import org.example.model.Person;
import org.example.repository.Repository;
import java.io.IOException;
import org.example.exception.InvalidDataException;


public class Main {
    public static void main(String[] args) {
        try {
            // Create a Repository
            Repository repository = new Repository("path/to/master/directory");

            // Create a Person
            Person person1 = new Person("Marian", "123");
            Person person2 = new Person("Cazan", "456");

            // Create a Document
            Document document1 = new Document("Document1", "path/to/master/directory/123/Document1.txt");
            Document document2 = new Document("Document2", "path/to/master/directory/456/Document2.txt");

            // Add the document to the person's repository
            try {
                repository.addDocument(person1, document1);
                repository.addDocument(person2, document2);
            } catch (InvalidDataException e) {
                System.out.println(e.getMessage());
            }

            repository.displayRepositoryContent();

            // Create a Shell and register commands
            Shell shell = new Shell();
            shell.registerCommand("view", new ViewCommand(new String[]{},repository));
            shell.registerCommand("report", new ReportCommand(new String[]{"report.html"}, repository));
            shell.registerCommand("export", new ExportCommand(new String[]{"export.json"}, repository));

            // Start the shell
            shell.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}