package sudoku;

public class SudokuRow extends SudokuFieldCollection {
    public SudokuRow(SudokuField[] board, int x) {
        System.arraycopy(board, x * 9, link, 0, link.length);
    }
}

