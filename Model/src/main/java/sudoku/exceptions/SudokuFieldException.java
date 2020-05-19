package sudoku.exceptions;

import java.util.ResourceBundle;

public class SudokuFieldException extends Exception {
    ResourceBundle bundle = ResourceBundle.getBundle("Language");

    public SudokuFieldException(String message) {
        super(message);
    }

    @Override
    public String getLocalizedMessage() {
        return bundle.getString(getMessage());
    }
}
