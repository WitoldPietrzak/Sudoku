package sudoku;

public class SudokuField {
    private int value;

    public final int getFieldValue() {
        return value;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
        this.value = 0;
    }

    public final void setFieldValue(int value) {
        this.value = value;
    }
}
