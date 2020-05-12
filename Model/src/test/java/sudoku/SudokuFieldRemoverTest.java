package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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