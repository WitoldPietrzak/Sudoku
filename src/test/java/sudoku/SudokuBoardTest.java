package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void testCorrect() {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.set(1, 1, 5);
        assertTrue(sudoku.get(1, 1) == 5);
        assertFalse(sudoku.get(1, 1) == 8);

    }
}