package org.example.shell;

import org.example.repository.Repository;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class ViewCommand extends Command {
    private final Repository repository;

    public ViewCommand(String[] args, Repository repository) {
        super(args);
        this.repository = repository;
    }

    @Override
    public void execute() {
        try {
            String projectDirectory = System.getProperty("user.dir");
            String fullPath = projectDirectory + "/" + repository.getPath() + "/" + args[0];
            Desktop.getDesktop().open(new File(fullPath));
        } catch (IOException e) {
            System.out.println("Error opening file: " + e.getMessage());
        }
    }
}