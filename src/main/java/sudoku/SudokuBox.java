package sudoku;

public class SudokuBox extends SudokuFieldCollection {
    int boxLenth = 3;

    public SudokuBox(SudokuField[] board, int x, int y) {
        x /= boxLenth;
        y /= boxLenth;
        for (int i = 0; i < boxLenth; i++) {
            for (int j = 0; j < boxLenth; j++) {
                link[i * boxLenth + j] = board[x * boxLenth + j + y * boxLenth * linkSize + linkSize * i];
            }
        }
    }
}
