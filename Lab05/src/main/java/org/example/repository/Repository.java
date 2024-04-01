package org.example.repository;

import org.example.model.Document;
import org.example.model.Person;
import org.example.exception.InvalidDataException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Repository {
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();

    public Repository(String directory) {
        this.directory = directory;
        loadDocuments();
    }

    public String getDirectory()
    {
        return directory;
    }

    public void setDirectory(String directory)
    {
        this.directory = directory;
    }

    public Map<Person, List<Document>> getDocuments()
    {
        return documents;
    }

    public void setDocuments(Map<Person, List<Document>> documents)
    {
        this.documents = documents;
    }

    private void loadDocuments() {
        try {
            Files.walk(Path.of(directory))
                    .filter(Files::isDirectory)
                    .forEach(path -> {
                        String personId = path.getFileName().toString();
                        // Check if the directory name is a valid Person ID
                        if (isValidPersonId(personId)) {
                            Person person = new Person(personId, personId);
                            List<Document> personDocuments = new ArrayList<>();
                            try {
                                Files.walk(path)
                                        .filter(Files::isRegularFile)
                                        .forEach(file -> {
                                            String documentName = file.getFileName().toString();
                                            Document document = new Document(documentName, file.toString());
                                            personDocuments.add(document);
                                        });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            documents.put(person, personDocuments);
                        }
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean isValidPersonId(String personId) {
        // Implement this method to check if a directory name is a valid Person ID
        // For example, you could check if the personId is a number
        try {
            Integer.parseInt(personId);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void displayRepositoryContent() throws IOException {
        if (Files.exists(Path.of(directory))) {
            Files.walk(Path.of(directory))
                    .filter(Files::isRegularFile)
                    .forEach(path -> {
                        String personId = path.getParent().getFileName().toString();
                        System.out.println("Person ID: " + personId + ", File: " + path);
                    });
        }
    }

    public void addDocument(Person person, Document document) throws InvalidDataException, IOException {
        Path personDirectory = Path.of(directory).resolve(person.id());
        Path documentDirectory = Path.of(document.filePath()).getParent();
        if (!Files.exists(personDirectory)) {
            throw new InvalidDataException("Person's directory does not exist: " + personDirectory);
        }
        if (!personDirectory.equals(documentDirectory)) {
            throw new InvalidDataException("Document's directory does not match the person's directory: " + documentDirectory);
        }
        Path documentPath = Path.of(document.filePath());
        Path targetPath = personDirectory.resolve(documentPath.getFileName().toString());
        Files.copy(documentPath, targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    public String getPath() {
        return this.directory;
    }
}