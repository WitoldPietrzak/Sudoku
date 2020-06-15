package pl.comp.dao.exceptions;

import java.util.ResourceBundle;

public class DaoReadException extends Exception {
    ResourceBundle bundle = ResourceBundle.getBundle("DaoException");

    public DaoReadException(String errorMessage) {
        super(errorMessage);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
