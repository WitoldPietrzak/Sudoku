package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {
    @Test
    void SudokuFieldConstructorTest() {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = new SudokuField();
        assertEquals(field.getFieldValue(), 5);
        assertEquals(field2.getFieldValue(), 0);
    }

    @Test
    void testToString() {
        SudokuField field = new SudokuField(5);
        assertNotNull(field.toString());
    }

    @Test
    void testEquals() {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertTrue(field.equals(field2) && field2.equals(field));
        field2.setFieldValue(4);
        assertFalse(field.equals(field2) && field2.equals(field));
        assertTrue(field.equals(field));
        SudokuBoard board = new SudokuBoard(new BacktrackingSudokuSolver());
        assertFalse(field.equals(board));
    }

    @Test
    void testHashCode() {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertEquals(field.hashCode(), field2.hashCode());
    }

    @Test
    void testCompareTo() {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        SudokuField field3 = new SudokuField(3);
        SudokuField field4 = new SudokuField(7);

        assertEquals(field.compareTo(field2),0);
        assertEquals(field.compareTo(field3),1);
        assertEquals(field.compareTo(field4),-1);
    }
    @Test
    void testCloning() throws CloneNotSupportedException {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = field.clone();
        field2.setFieldValue(3);

        assertNotEquals(field, field2);
    }
}