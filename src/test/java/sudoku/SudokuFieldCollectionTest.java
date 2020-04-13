package sudoku;

import org.junit.jupiter.api.BeforeEach;
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
}