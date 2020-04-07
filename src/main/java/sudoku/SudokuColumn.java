package sudoku;

import java.util.List;

public class SudokuColumn extends SudokuFieldCollection {
    public SudokuColumn(List<SudokuField> board, int y) {
        for (int i = 0; i < link.size();i++) {
            link.set(i, board.get(y + i * linkSize));
        }
    }
}
