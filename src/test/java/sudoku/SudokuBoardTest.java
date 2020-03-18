package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuBoardTest {

    @Test
    void testCorrect() {
        SudokuBoard sudoku = new SudokuBoard();
        sudoku.fillBoard();
        assertTrue(sudoku.checkBoard());

    }

    @Test
    void testIfNotEqual() {
        SudokuBoard sudoku1 = new SudokuBoard();
        sudoku1.fillBoard();
        SudokuBoard sudoku2 = new SudokuBoard();
        sudoku2.fillBoard();
        boolean equal = true;
        for (int i = 0; i < sudoku1.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku1.getSudokuSize(); j++) {
                if (sudoku1.getBoard(i, j) != sudoku2.getBoard(i, j)) {
                    equal = false;
                    break;
                }
            }
        }
        assertFalse(equal);
    }
}