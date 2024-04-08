package org.example.shell;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.example.model.Person;
import org.example.model.PersonKeySerializer;
import org.example.repository.Repository;

import java.io.IOException;
import java.nio.file.Paths;

public class ExportCommand extends Command
{
    private Repository repository;
    private String outputPath;

    public ExportCommand(String[] args, Repository repository)
    {
        super(args);
        this.repository = repository;
        this.outputPath = (args != null && args.length > 0) ? args[0] : "export.json";  //default
    }

    @Override
    public void execute()
    {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.INDENT_OUTPUT, true);

        // Register the custom serializer
        SimpleModule module = new SimpleModule();
        module.addKeySerializer(Person.class, new PersonKeySerializer());
        mapper.registerModule(module);

        try
        {
            mapper.writeValue(Paths.get(outputPath).toFile(), repository);
        } catch (IOException e)
        {
            System.out.println("Error exporting repository: " + e.getMessage());
        }
    }
}