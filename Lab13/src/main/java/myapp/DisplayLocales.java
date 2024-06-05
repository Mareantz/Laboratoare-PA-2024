package myapp;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayLocales {
    private ResourceBundle messages;

    public DisplayLocales(ResourceBundle messages) {
        this.messages = messages;
    }

    public void execute() {
        System.out.println(messages.getString("locales"));
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            System.out.println(locale.toString() + " - " + locale.getDisplayName());
        }
    }
}
