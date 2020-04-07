package sudoku;

import java.util.Arrays;
import java.util.List;

public abstract class SudokuFieldCollection {
    protected int linkSize = 9;
    protected List<SudokuField> link = Arrays.asList(new SudokuField[linkSize]);

    public boolean verify() {
        boolean[] checker = new boolean[link.size() + 1];
        for (SudokuField sudokuField : link) {
            if (checker[sudokuField.getFieldValue()]
                    && (sudokuField.getFieldValue() != 0)) {
                return false;
            }
            checker[sudokuField.getFieldValue()] = true;
        }
        return true;

    }

}
