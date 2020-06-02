package pl.comp.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

public final class LocaleController {
    private static final LocaleController INSTANCE = new LocaleController();

    private static Locale locale = getDefaultLocale();

    private LocaleController(){

    }

    public static LocaleController getInstance() {
        return INSTANCE;
    }

    public static Locale getLocale() {
        return locale;
    }

    public static void setLocale(Locale locale) {
        LocaleController.locale = locale;
    }

    public static List<Locale> getAvailableLocales() {
        return new ArrayList<>(Arrays.asList(new Locale("en"), new Locale("pl")));
    }

    public static Locale getDefaultLocale() {
        Locale defaultLocale = Locale.getDefault();
        return getAvailableLocales().contains(defaultLocale) ? defaultLocale : new Locale("en");
    }
}
