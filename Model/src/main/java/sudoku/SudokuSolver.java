package sudoku;

/**
 *  Interface solving Sudoku Boards.
 */
public interface SudokuSolver {
    /**
     * Solves board.
     * @param sudoku The board to solve.
     */
    void solve(SudokuBoard sudoku);
}

