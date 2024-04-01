package org.example.shell;

import org.example.exception.InvalidCommandException;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Shell {
    private final Map<String, Command> commands = new HashMap<>();

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("> ");
            String line = scanner.nextLine();
            String[] parts = line.split("\\s+");
            if (parts.length > 0) {
                Command command = commands.get(parts[0]);
                if (command != null) {
                    String[] args = new String[parts.length - 1];
                    System.arraycopy(parts, 1, args, 0, parts.length - 1);
                    command.setArgs(args);
                    command.execute();
                } else {
                    try {
                        throw new InvalidCommandException("Unknown command: " + parts[0]);
                    } catch (InvalidCommandException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }
}