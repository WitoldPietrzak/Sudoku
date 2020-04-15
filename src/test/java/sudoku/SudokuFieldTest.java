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
    }

    @Test
    void testHashCode() {
        SudokuField field = new SudokuField(5);
        SudokuField field2 = new SudokuField(5);
        assertEquals(field.hashCode(), field2.hashCode());
    }
}