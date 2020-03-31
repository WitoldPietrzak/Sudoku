package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        SudokuBoard sudoku = new SudokuBoard();
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++){
                sudoku.set(i,j,0);
            }
        }
        solver.solve(sudoku);
        assertTrue(sudoku.checkBoard());
    }

    @Test
    void testIfNotEqual() {
        BacktrackingSudokuSolver solver = new BacktrackingSudokuSolver();
        SudokuBoard sudoku1 = new SudokuBoard();
        solver.solve(sudoku1);
        SudokuBoard sudoku2 = new SudokuBoard();
        solver.solve(sudoku2);
        boolean equal = true;
        for (int i = 0; i < sudoku1.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku1.getSudokuSize(); j++) {
                if (sudoku1.get(i, j) != sudoku2.get(i, j)) {
                    equal = false;
                    break;
                }
            }
        }
        assertFalse(equal);
    }
}