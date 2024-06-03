package myapp;

import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.util.Currency;
import java.util.Locale;
import java.util.ResourceBundle;

public class Info {
    private ResourceBundle messages;

    public Info(ResourceBundle messages) {
        this.messages = messages;
    }

    public void execute() {
        Locale locale = Locale.getDefault();
        displayLocaleInfo(locale);
    }

    public void execute(String languageTag) {
        Locale locale = Locale.forLanguageTag(languageTag);
        displayLocaleInfo(locale);
    }

    private void displayLocaleInfo(Locale locale) {
        ResourceBundle bundle = ResourceBundle.getBundle("res.Messages", locale);

        System.out.println(bundle.getString("info").replace("{0}", locale.toString()));
        System.out.println("Country: " + locale.getDisplayCountry() + " (" + locale.getDisplayCountry(locale) + ")");
        System.out.println("Language: " + locale.getDisplayLanguage() + " (" + locale.getDisplayLanguage(locale) + ")");

        try {
            Currency currency = Currency.getInstance(locale);
            System.out.println("Currency: " + currency.getCurrencyCode() + " (" + currency.getDisplayName(locale) + ")");
        } catch (IllegalArgumentException e) {
            System.out.println("Currency: Not available for this locale");
        }

        DateFormatSymbols symbols = new DateFormatSymbols(locale);
        System.out.println("Week Days: " + String.join(", ", symbols.getWeekdays()));
        System.out.println("Months: " + String.join(", ", symbols.getMonths()));

        DateFormat dateFormat = DateFormat.getDateInstance(DateFormat.LONG, locale);
        System.out.println("Today: " + dateFormat.format(new java.util.Date()));
    }
}
