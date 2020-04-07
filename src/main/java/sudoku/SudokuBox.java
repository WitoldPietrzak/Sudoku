package sudoku;

import java.util.List;

public class SudokuBox extends SudokuFieldCollection {
    int boxLenth = 3;

    public SudokuBox(List<SudokuField> board, int x, int y) {
        x /= boxLenth;
        y /= boxLenth;
        for (int i = 0; i < boxLenth; i++) {
            for (int j = 0; j < boxLenth; j++) {
                link.set(i * boxLenth + j,
                        board.get(x * boxLenth + j + y * boxLenth * linkSize + linkSize * i));
            }
        }
    }
}
