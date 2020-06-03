package pl.comp.gui;

import java.util.Locale;
import javafx.util.converter.NumberStringConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class ModifiedNumberStringConverter extends NumberStringConverter {
    private final Locale local = Locale.getDefault(Locale.Category.FORMAT);
    private final NumberStringConverter converter = new NumberStringConverter(local);
    private static final Logger logger = LoggerFactory.getLogger(ModifiedNumberStringConverter.class);

    @Override
    public Number fromString(String value) {
        if (value.equals("")) {
            value = value.replace("", "0");
        }
        try {
            return converter.fromString(value);
        } catch (NumberFormatException e) {
           logger.error("NumberFormatException");
        }
        return -1.0;


    }

    @Override
    public String toString(Number value) {

        try {
            if (value.equals(0)) {
                value = null;
            }
            return converter.toString(value);
        } catch (NumberFormatException e) {
            logger.error("NumberFormatException");
        }

        return null;
    }

}
