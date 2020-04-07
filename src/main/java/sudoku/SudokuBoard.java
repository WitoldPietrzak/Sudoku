package sudoku;

import java.util.Arrays;
import java.util.List;

/**
 * Class Generating valid 9 by 9 sudoku board.
 */
public class SudokuBoard {
    /**
     * Size of sudoku board.
     */
    private final int sudokuSize = 9;
    /**
     * the board to which sudoku numbers from 1 to 9 are being written.
     */
    private List<SudokuField> board = Arrays.asList(new SudokuField[sudokuSize * sudokuSize]);
    /**
     * solver, which is used to solve sudoku.
     */
    SudokuSolver solver = new BacktrackingSudokuSolver();

    /**
     * Returns the size of the board.
     *
     * @return Returns the size of the board.
     */
    public final int getSudokuSize() {
        return sudokuSize;
    }


    /**
     * Function returns the value of given field.
     *
     * @param rowNumber    Row number of field.
     * @param columnNumber Column number of field.
     * @return returns the value of given field.
     */
    public final int get(final int rowNumber, final int columnNumber) {
        return board.get(rowNumber * sudokuSize + columnNumber).getFieldValue();
    }

    /**
     * Function sets the value of given field.
     *
     * @param rowNumber    Row number of field.
     * @param columnNumber Column number of field.
     * @param value        value to assing to given field.
     */
    public final void set(final int rowNumber,
                          final int columnNumber, final int value) {

        board.get(rowNumber * sudokuSize + columnNumber).setFieldValue(value);
    }

    public SudokuBoard() {
        for (int i = 0; i < this.board.size(); i++) {
            this.board.set(i, new SudokuField());
        }
    }

    public final SudokuRow getRow(int x) {
        return new SudokuRow(board, x);
    }

    public final SudokuColumn getColumn(int y) {
        return new SudokuColumn(board, y);
    }

    public final SudokuBox getBox(int x, int y) {
        return new SudokuBox(board, x, y);
    }

    /**
     * Checks if board is valid sudoku board.
     *
     * @return true if board is valid sudoku board, false if is not.
     */
    public final boolean checkBoard() {
        for (int i = 0; i < sudokuSize; i++) {
            if (!(getColumn(i).verify() && getRow(i).verify() && getBox(i, i).verify())) {
                return false;
            }
        }
        return true;
    }

    /**
     * Solves sudoku board using BacktrackingSudokuSolver.
     */
    public final void solveGame() {
        solver.solve(this);
    }


}

