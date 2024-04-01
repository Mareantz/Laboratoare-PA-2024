package org.example.shell;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.example.repository.Repository;

import java.awt.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URI;
import java.util.Properties;

public class ReportCommand extends Command {
    private Repository repository;

    public ReportCommand(String[] args, Repository repository) {
        super(args);
        this.repository = repository;
    }

    @Override
    public void execute() {
        Properties properties = new Properties();
        properties.setProperty("resource.loader", "class");
        properties.setProperty("class.resource.loader.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");

        VelocityEngine velocityEngine = new VelocityEngine(properties);
        Template template = velocityEngine.getTemplate("/templates/report.vm");

        VelocityContext context = new VelocityContext();
        context.put("repository", repository);

        try {
            File htmlReport = new File("report.html");
            Writer fileWriter = new FileWriter(htmlReport);
            template.merge(context, fileWriter);
            fileWriter.close();

            // Open the report in the default browser
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                Desktop.getDesktop().browse(htmlReport.toURI());
            }
        } catch (IOException e) {
            System.out.println("Error generating report: " + e.getMessage());
        }
    }
}