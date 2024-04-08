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
import java.util.HashSet;
import java.util.Set;
import java.util.Collections;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class Repository
{
    private String directory;
    private Map<Person, List<Document>> documents = new HashMap<>();
    private Map<Person, Set<String>> personAbilities = new HashMap<>();


    public Repository(String directory)
    {
        this.directory = directory;
        loadDocuments();
        loadAbilities();
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

    private void loadDocuments()
    {
        try
        {
            Files.walk(Path.of(directory))
                    .filter(Files::isDirectory)
                    .forEach(path ->
                    {
                        String personId = path.getFileName().toString();
                        if (isValidPersonId(personId))
                        {
                            Person person = new Person(personId, personId);
                            List<Document> personDocuments = new ArrayList<>();
                            try
                            {
                                Files.walk(path)
                                        .filter(Files::isRegularFile)
                                        .forEach(file ->
                                        {
                                            String documentName = file.getFileName().toString();
                                            Document document = new Document(documentName, file.toString());
                                            personDocuments.add(document);
                                        });
                            } catch (IOException e)
                            {
                                e.printStackTrace();
                            }
                            documents.put(person, personDocuments);
                        }
                    });
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private boolean isValidPersonId(String personId)
    {
        try
        {
            Integer.parseInt(personId);
            return true;
        } catch (NumberFormatException e)
        {
            return false;
        }
    }

    public void displayRepositoryContent() throws IOException
    {
        if (Files.exists(Path.of(directory)))
        {
            Files.walk(Path.of(directory))
                    .filter(Files::isRegularFile)
                    .forEach(path ->
                    {
                        String personId = path.getParent().getFileName().toString();
                        System.out.println("Person ID: " + personId + ", File: " + path);
                    });
        }
    }

    public void addDocument(Person person, Document document) throws InvalidDataException, IOException
    {
        Path personDirectory = Path.of(directory).resolve(person.id());
        Path documentDirectory = Path.of(document.filePath()).getParent();
        if (!Files.exists(personDirectory))
        {
            throw new InvalidDataException("Person's directory does not exist: " + personDirectory);
        }
        if (!personDirectory.equals(documentDirectory))
        {
            throw new InvalidDataException("Document's directory does not match the person's directory: " + documentDirectory);
        }
        Path documentPath = Path.of(document.filePath());
        Path targetPath = personDirectory.resolve(documentPath.getFileName().toString());
        Files.copy(documentPath, targetPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING);
    }

    public String getPath()
    {
        return this.directory;
    }

    private void loadAbilities()
    {
        try (FileInputStream fis = new FileInputStream(directory + "/abilities.xlsx");
             Workbook workbook = new XSSFWorkbook(fis))
        {
            Sheet sheet = workbook.getSheetAt(0);
            boolean firstRow = true;
            for (Row row : sheet)
            {
                if (firstRow)
                {
                    firstRow = false;
                    continue;
                }
                String cellContent = row.getCell(0).getStringCellValue();
                String[] splitContent = cellContent.split(" ");
                String personId = splitContent[0];
                Person person = new Person(personId, personId);
                Set<String> abilities = new HashSet<>();
                if (splitContent.length > 1)
                {
                    String[] abilitiesArray = splitContent[1].split(",");
                    for (String ability : abilitiesArray)
                    {
                        abilities.add(ability.trim());
                    }
                }
                if (row.getLastCellNum() > 1)
                {
                    String[] abilitiesArray = row.getCell(1).getStringCellValue().split(",");
                    for (String ability : abilitiesArray)
                    {
                        abilities.add(ability.trim());
                    }
                }
                personAbilities.put(person, abilities);
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public List<Set<Person>> findMaximalGroups()
    {
        List<Set<Person>> maximalGroups = new ArrayList<>();
        for (Person person : personAbilities.keySet())
        {
            boolean added = false;
            for (Set<Person> group : maximalGroups)
            {
                if (group.stream().allMatch(p -> !Collections.disjoint(personAbilities.get(p), personAbilities.get(person))))
                {
                    group.add(person);
                    added = true;
                    break;
                }
            }
            if (!added)
            {
                Set<Person> newGroup = new HashSet<>();
                newGroup.add(person);
                maximalGroups.add(newGroup);
            }
        }
        return maximalGroups;
    }
}