package org.example.repository;

import org.example.model.Document;
import org.example.model.Person;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    private final Path masterDirectory;

    public Repository(String masterDirectoryPath) {
        this.masterDirectory = Path.of(masterDirectoryPath);
    }

    public void createRepository(Person person) throws IOException {
        Path personDirectory = masterDirectory.resolve(person.id());
        if (!Files.exists(personDirectory)) {
            Files.createDirectories(personDirectory);
        }
    }

    public List<Document> getDocuments(Person person) throws IOException {
        List<Document> documents = new ArrayList<>();
        Path personDirectory = masterDirectory.resolve(person.id());
        if (Files.exists(personDirectory)) {
            Files.walk(personDirectory)
                    .filter(Files::isRegularFile)
                    .map(Path::toFile)
                    .map(file -> new Document(file.getName(), file.getPath()))
                    .forEach(documents::add);
        }
        return documents;
    }

    public void displayRepositoryContent() throws IOException {
        if (Files.exists(masterDirectory)) {
            Files.walk(masterDirectory)
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String personId = path.getParent().getFileName().toString();
                        System.out.println("Person ID: " + personId + ", File: " + path);
                    });
        }
    }

    public void addDocument(Person person, Document document) throws IOException {
        Path personDirectory = masterDirectory.resolve(person.id());
        Path documentDirectory = Path.of(document.filePath()).getParent();
        if (Files.exists(personDirectory) && personDirectory.equals(documentDirectory)) {
            Path documentPath = Path.of(document.filePath());
            Path targetPath = personDirectory.resolve(documentPath.getFileName().toString());
            Files.copy(documentPath, targetPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }

}