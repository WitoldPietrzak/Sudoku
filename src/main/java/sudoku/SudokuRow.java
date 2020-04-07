package sudoku;

import java.util.List;

public class SudokuRow extends SudokuFieldCollection {
    public SudokuRow(List<SudokuField> board, int x) {
        for (int i = 0;i < linkSize;i++) {
            link.set(i, board.get((x * 9) + i));
        }
    }
}

