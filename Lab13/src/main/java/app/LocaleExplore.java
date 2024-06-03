package app;

import myapp.DisplayLocales;
import myapp.SetLocale;
import myapp.Info;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class LocaleExplore {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ResourceBundle messages = ResourceBundle.getBundle("res.Messages", Locale.getDefault());

        while (true) {
            System.out.println(messages.getString("prompt"));
            String command = scanner.nextLine().trim();

            if (command.equalsIgnoreCase("locales")) {
                new DisplayLocales(messages).execute();
            } else if (command.toLowerCase().startsWith("set")) {
                String[] parts = command.split(" ");
                if (parts.length == 2) {
                    new SetLocale(messages).execute(parts[1]);
                    messages = ResourceBundle.getBundle("res.Messages", Locale.getDefault());
                } else {
                    System.out.println(messages.getString("invalid"));
                }
            } else if (command.toLowerCase().startsWith("info")) {
                String[] parts = command.split(" ");
                if (parts.length == 1) {
                    new Info(messages).execute();
                } else if (parts.length == 2) {
                    new Info(messages).execute(parts[1]);
                } else {
                    System.out.println(messages.getString("invalid"));
                }
            } else {
                System.out.println(messages.getString("invalid"));
            }
        }
    }
}
