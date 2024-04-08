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

import java.util.Set;
import java.util.List;


public class Main
{
    public static void main(String[] args)
    {
        try
        {

            Repository repository = new Repository("path/to/master/directory");


            Person person1 = new Person("Marian", "123");
            Person person2 = new Person("Cazan", "456");


            Document document1 = new Document("Document1", "path/to/master/directory/123/Document1.txt");
            Document document2 = new Document("Document2", "path/to/master/directory/456/Document2.txt");


            try
            {
                repository.addDocument(person1, document1);
                repository.addDocument(person2, document2);
            } catch (InvalidDataException e)
            {
                System.out.println(e.getMessage());
            }

            repository.displayRepositoryContent();

            // Find maximal groups
            List<Set<Person>> maximalGroups = repository.findMaximalGroups();

            for (int i = 0; i < maximalGroups.size(); i++)
            {
                System.out.println("Group " + (i + 1) + ":");
                for (Person person : maximalGroups.get(i))
                {
                    System.out.println(person.name());
                }
                System.out.println();
            }

            Shell shell = new Shell();
            shell.registerCommand("view", new ViewCommand(new String[]{}, repository));
            shell.registerCommand("report", new ReportCommand(new String[]{"report.html"}, repository));
            shell.registerCommand("export", new ExportCommand(new String[]{"export.json"}, repository));

            shell.start();


        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}