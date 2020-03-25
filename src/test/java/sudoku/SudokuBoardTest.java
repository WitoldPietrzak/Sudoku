package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void testCorrect() {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.set(1, 1, 5);
        assertEquals(5, sudoku.get(1, 1));
        assertNotEquals(8, sudoku.get(1, 1));
        assertEquals(sudoku.getBlockSize(), 3);
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        solver.solve(sudoku);
        sudoku.set(0, 8, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        solver.solve(sudoku);
        sudoku.set(8, 0, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        solver.solve(sudoku);
        sudoku.set(2, 2, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
    }
}