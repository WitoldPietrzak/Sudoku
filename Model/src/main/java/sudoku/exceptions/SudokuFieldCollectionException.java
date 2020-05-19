package sudoku.exceptions;

import java.util.ResourceBundle;

public class SudokuFieldCollectionException extends Exception {
    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public SudokuFieldCollectionException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
