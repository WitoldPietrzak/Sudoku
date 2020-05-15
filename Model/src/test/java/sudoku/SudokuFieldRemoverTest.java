package sudoku;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class SudokuFieldRemoverTest {

    @Test
    void remove() throws CloneNotSupportedException {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuFieldRemover remover = new SudokuFieldRemover();
        sudoku.solveGame();
        remover.remove(sudoku,DifficultyLevel.Easy);
        assertTrue(sudoku.checkBoard());
    }
}