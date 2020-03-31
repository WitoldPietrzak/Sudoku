package sudoku;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SudokuFieldTest {

    @Test
    void SudokuFieldConstructorTest() {
        SudokuField field = new SudokuField(5);
        assertEquals(field.getFieldValue(), 5);
        SudokuField field2 = new SudokuField();
        assertEquals(field2.getFieldValue(), 0);


    }
}