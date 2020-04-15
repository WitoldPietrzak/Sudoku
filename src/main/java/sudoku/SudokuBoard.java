package sudoku;

import java.util.Arrays;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Class Generating valid 9 by 9 sudoku board.
 */
public class SudokuBoard {
    /**
     * Size of sudoku board.
     */
    private static final int SUDOKU_SIZE = 9;
    /**
     * the board to which sudoku numbers from 1 to 9 are being written.
     */
    private List<SudokuField> board = Arrays.asList(new SudokuField[SUDOKU_SIZE * SUDOKU_SIZE]);
    /**
     * solver, which is used to solve sudoku.
     */
    private SudokuSolver solver;
    /**
     * Returns the size of the board.
     *
     * @return Returns the size of the board.
     */

    public final int getSudokuSize() {
        return SUDOKU_SIZE;
    }

    /**
     * Function returns the value of given field.
     *
     * @param rowNumber    Row number of field.
     * @param columnNumber Column number of field.
     * @return returns the value of given field.
     */
    public final int get(final int rowNumber, final int columnNumber) {
        return board.get(rowNumber * SUDOKU_SIZE + columnNumber).getFieldValue();
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

        board.get(rowNumber * SUDOKU_SIZE + columnNumber).setFieldValue(value);
    }

    public SudokuBoard(SudokuSolver solver) {
        this.solver = solver;
        for (int i = 0; i < this.board.size(); i++) {
            this.board.set(i, new SudokuField());
        }
    }

    public final SudokuRow getRow(int x) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SUDOKU_SIZE]);
        for (int i = 0; i < getSudokuSize(); i++) {
            fields.set(i, board.get((x * 9) + i));
        }
        return new SudokuRow(fields);
    }

    public final SudokuColumn getColumn(int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SUDOKU_SIZE]);
        for (int i = 0; i < SUDOKU_SIZE; i++) {
            fields.set(i, board.get(y + i * SUDOKU_SIZE));
        }
        return new SudokuColumn(fields);
    }

    public final SudokuBox getBox(int x, int y) {
        List<SudokuField> fields = Arrays.asList(new SudokuField[SUDOKU_SIZE]);
        x /= SudokuBox.BOX_SIZE;
        y /= SudokuBox.BOX_SIZE;
        for (int i = 0; i < SudokuBox.BOX_SIZE; i++) {
            for (int j = 0; j < SudokuBox.BOX_SIZE; j++) {
                fields.set(i * SudokuBox.BOX_SIZE + j,
                        board.get(x * SudokuBox.BOX_SIZE + j + y
                                * SudokuBox.BOX_SIZE * SUDOKU_SIZE + SUDOKU_SIZE * i));
            }
        }
        return new SudokuBox(fields);
    }

    /**
     * Checks if board is valid sudoku board.
     *
     * @return true if board is valid sudoku board, false if is not.
     */
    public final boolean checkBoard() {
        for (int i = 0; i < SUDOKU_SIZE; i++) {
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

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Board", board).toString();
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(board, ((SudokuBoard) o).board).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(13, 21).append(board).toHashCode();
    }
}

