package myapp;

import java.util.Locale;
import java.util.ResourceBundle;

public class SetLocale {
    private ResourceBundle messages;

    public SetLocale(ResourceBundle messages) {
        this.messages = messages;
    }

    public void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        if (locale.getLanguage().isEmpty()) {
            System.out.println(messages.getString("invalid"));
            return;
        }
        Locale.setDefault(locale);
        System.out.println(messages.getString("locale.set").replace("{0}", locale.toString()));
    }
}
