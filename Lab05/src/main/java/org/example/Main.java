package org.example;

import org.example.model.Document;
import org.example.model.Person;
import org.example.repository.Repository;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        // Create a Person
        Person person1 = new Person("John Doe", "123");
        Person person2 = new Person("Jane Doe", "456");


        // Create a Repository
        Repository repository = new Repository("path/to/master/directory");

        // Create a Document
        Document document1 = new Document("Document1", "path/to/master/directory/123/Document1.txt");
        Document document2 = new Document("Document2", "path/to/master/directory/456/Document2.txt");

        try {
            // Create a repository for the person
            repository.createRepository(person1);

            // Add the document to the person's repository
            repository.addDocument(person1, document1);
            repository.addDocument(person2, document2);

            // Display the content of the repository
            repository.displayRepositoryContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}