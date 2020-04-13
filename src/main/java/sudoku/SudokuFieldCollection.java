package sudoku;

import java.util.List;

public abstract class SudokuFieldCollection {
    public static final int linkSize = 9;
    private List<SudokuField> link;

    public SudokuFieldCollection(List<SudokuField> fields) {
        this.link = fields;
    }

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
