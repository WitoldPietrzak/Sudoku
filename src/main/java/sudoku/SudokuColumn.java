package sudoku;

public class SudokuColumn extends SudokuFieldCollection {
    public SudokuColumn(SudokuField[] board, int y) {
        for (int i = 0; i < link.length; i++) {
            link[i] = board[y + i * linkSize];
        }
    }
}
