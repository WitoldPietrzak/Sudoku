package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import sudoku.exceptions.SudokuFieldException;


class SudokuBoardTest {

    @Test
    void testCorrect() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku.set(1, 1, 5);
        assertEquals(5, sudoku.get(1, 1));
        assertNotEquals(8, sudoku.get(1, 1));
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        sudoku.solveGame();
        sudoku.set(0, 8, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        sudoku.solveGame();
        sudoku.set(8, 0, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                sudoku.set(i, j, 0);
            }
        }
        sudoku.solveGame();
        sudoku.set(2, 2, sudoku.get(0, 0));
        assertFalse(sudoku.checkBoard());
    }

    @Test
    void testToString() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        assertNotNull(sudoku.toString());
    }

    @Test
    void testEquals() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
        assertTrue(sudoku.equals(sudoku));
        assertTrue(sudoku.equals(sudoku1) && sudoku1.equals(sudoku));
        sudoku.set(3, 3, 5);
        assertFalse(sudoku.equals(sudoku1) && sudoku1.equals(sudoku));
        SudokuField sudokuPole = new SudokuField(4);
        assertFalse(sudoku.equals(sudokuPole));
    }

    @Test
    void testHashCode() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudoku1 = new SudokuBoard(new BacktrackingSudokuSolver());
        assertEquals(sudoku.hashCode(), sudoku1.hashCode());
    }

    @Test
    void testClonable() throws CloneNotSupportedException {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuBoard sudoku1 = sudoku.clone();
        sudoku1.set(0, 0, 5);
        assertNotEquals(sudoku, sudoku1);
    }

    @Test
    void testGetField() throws SudokuFieldException {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        SudokuField field = new SudokuField();
        field.setValue(0);
        assertEquals(field, sudoku.getField(0, 0));
        field.setValue(1);
        assertNotEquals(field, sudoku.getField(0, 0));
    }

    @Test
    void testCheckIfSolved() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        assertFalse(sudoku.checkIfSolved());
        sudoku.solveGame();
        assertTrue(sudoku.checkIfSolved());

    }

    @Test
    void testCheckIfEmpty() {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        assertTrue(sudoku.checkIfEmpty());
        sudoku.set(0,1,3);
        assertFalse(sudoku.checkIfEmpty());

    }


}