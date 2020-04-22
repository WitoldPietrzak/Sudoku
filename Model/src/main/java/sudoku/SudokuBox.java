package sudoku;

import java.util.List;

public class SudokuBox extends SudokuFieldCollection {
    public static final int BOX_SIZE = 3;

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }
}
