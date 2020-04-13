package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BacktrackingSudokuSolverTest {

    @Test
    void solve() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++){
                sudoku.set(i,j,0);
            }
        }
        sudoku.solveGame();
        assertTrue(sudoku.checkBoard());
    }

    @Test
    void testIfNotEqual() {
        SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku1.solveGame();
        SudokuBoard sudoku2 = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku2.solveGame();
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