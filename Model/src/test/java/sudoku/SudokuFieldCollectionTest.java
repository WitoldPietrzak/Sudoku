package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;


class SudokuFieldCollectionTest {
    protected List<SudokuField> link;

    private SudokuRow makeValidSudokuRow() {
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

    private SudokuColumn makeValidSudokuColumn() {
        return new SudokuColumn(Arrays.asList(
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

    private SudokuBox makeValidSudokuBox() {
        return new SudokuBox(Arrays.asList(
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

    private SudokuRow makeInvalidSudokuRow() {
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

    private SudokuRow makeInvalidSize() {
        return new SudokuRow(Arrays.asList(
                new SudokuField(1),
                new SudokuField(2),
                new SudokuField(3),
                new SudokuField(4),
                new SudokuField(5),
                new SudokuField(6),
                new SudokuField(7),
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

    @Test
    void testCloning() throws CloneNotSupportedException {
        SudokuRow row1 = makeValidSudokuRow();
        SudokuRow row2 = row1.clone();
        row2.link.set(0, new SudokuField(7));
        assertNotEquals(row1, row2);

        SudokuColumn col1 = makeValidSudokuColumn();
        SudokuColumn col2 = col1.clone();
        col2.link.set(0, new SudokuField(7));
        assertNotEquals(col1, col2);

        SudokuBox box1 = makeValidSudokuBox();
        SudokuBox box2 = box1.clone();
        box2.link.set(0, new SudokuField(9));
        assertNotEquals(box1, box2);

    }
}