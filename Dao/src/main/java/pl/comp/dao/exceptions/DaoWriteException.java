package pl.comp.dao.exceptions;

import java.util.ResourceBundle;

public class DaoWriteException extends Exception {
    ResourceBundle bundle = ResourceBundle.getBundle("DaoException");

    public DaoWriteException(String errorMessage, Exception cause) {
        super(errorMessage, cause);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
