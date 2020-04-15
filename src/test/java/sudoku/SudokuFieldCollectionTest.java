package sudoku;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldCollectionTest {
    private SudokuRow makeValidSudokuRow () {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }
    private SudokuRow makeInvalidSudokuRow () {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(1),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
                new SudokuField(8),
                new SudokuField(9)));
    }
    @Test
    void verifyValidRowTest() {
        SudokuRow validRow = makeValidSudokuRow();
        assertTrue(validRow.verify());
    }

    @Test
    void verifyInvalidRowTest() {
        SudokuRow invalidRow = makeInvalidSudokuRow();
        assertFalse(invalidRow.verify());
    }

    @Test
    void testToString() {
        SudokuRow row = makeValidSudokuRow();
        assertNotNull(row.toString());
    }

    @Test
    void testEquals() {
        SudokuRow row1 = makeValidSudokuRow();
        SudokuRow row2 = makeValidSudokuRow();
        assertTrue(row1.equals(row2) && row2.equals(row1));
        row2 = makeInvalidSudokuRow();
        assertFalse(row1.equals(row2) && row2.equals(row1));
        assertTrue(row1.equals(row1));
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        assertFalse(row1.equals(board));
    }

    @Test
    void testHashCode() {
        SudokuRow row1 = makeValidSudokuRow();
        SudokuRow row2 = makeValidSudokuRow();
        assertEquals(row1.hashCode(), row2.hashCode());
    }
}