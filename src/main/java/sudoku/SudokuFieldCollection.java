package sudoku;

public abstract class SudokuFieldCollection {
    protected int linkSize = 9;
    protected SudokuField[] link = new SudokuField[linkSize];

    public boolean verify() {
        boolean[] checker = new boolean[link.length + 1];
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
