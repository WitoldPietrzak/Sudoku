package sudoku;


/**
 * Class Generating valid 9 by 9 sudoku board.
 */
public class SudokuBoard {
    /**
     * Size of sudoku board.
     */
    private final int sudokuSize = 9;
    /**
     * Size of one block in the board.
     */
    private final int blockSize = 3;
    /**
     * the board to which sudoku numbers from 1 to 9 are being written.
     */
    private int[][] board = new int[sudokuSize][sudokuSize];

    /**
     * Returns the size of the board.
     *
     * @return Returns the size of the board.
     */
    public final int getSudokuSize() {
        return sudokuSize;
    }

    /**
     * Returns the size of a block.
     *
     * @return Returns the size of a block.
     */
    public final int getBlockSize() {
        return blockSize;
    }

    /**
     * Function returns the value of given field.
     *
     * @param rowNumber    Row number of field.
     * @param columnNumber Column number of field.
     * @return returns the value of given field.
     */
    public final int get(final int rowNumber, final int columnNumber) {
        return board[rowNumber][columnNumber];
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
        board[rowNumber][columnNumber] = value;
    }

    /**
     * Function checks if numbers make valid sudoku column.
     *
     * @param columnNumber is the number of column for which the check is done.
     * @return function returns true if the columns is valid, false if not.
     */
    public final boolean checkColumn(final int columnNumber) {
        boolean[] checker = new boolean[sudokuSize + 1];
        for (int i = 0; i < sudokuSize; i++) {
            if (checker[board[i][columnNumber]]
                    && (board[i][columnNumber] != 0)) {
                return false;
            }
            checker[board[i][columnNumber]] = true;
        }
        return true;

    }

    /**
     * Function checks if numbers make valid sudoku row.
     *
     * @param rowNumber is the number of row for which the check is done.
     * @return function returns true if the row is valid, false if not.
     */
    public final boolean checkRow(final int rowNumber) {
        boolean[] checker = new boolean[sudokuSize + 1];
        for (int i = 0; i < sudokuSize; i++) {
            if (checker[board[rowNumber][i]] && (board[rowNumber][i] != 0)) {
                return false;
            }
            checker[board[rowNumber][i]] = true;

        }
        return true;
    }

    /**
     * Function checks if numbers make valid sudoku 3 x 3 block.
     *
     * @param rowNumber is the number of column for which the check is done.
     * @param colNumber is the number of row for which the check is done.
     * @return function returns true if the block is valid, false if not.
     */
    public final boolean checkBlock(final int rowNumber, final int colNumber) {
        int blockRow = rowNumber / blockSize;
        int blockCol = colNumber / blockSize;
        boolean[] checker = new boolean[sudokuSize + 1];
        for (int i = blockRow * blockSize;
             i < blockRow * blockSize + blockSize; i++) {
            for (int j = blockCol * blockSize;
                 j < blockCol * blockSize + blockSize; j++) {
                if (checker[board[i][j]] && (board[i][j] != 0)) {
                    return false;
                }
                checker[board[i][j]] = true;
            }
        }
        return true;
    }

    /**
     * Checks if board is valid sudoku board.
     *
     * @return true if board is valid sudoku board, false if is not.
     */
    public final boolean checkBoard() {
        for (int i = 0; i < sudokuSize; i++) {
            if (!(checkColumn(i) && checkRow(i) && checkBlock(i, i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * Draws the sudoku board.
     */
    public final void printBoard() {
        System.out.print("\n");
        for (int i = 0; i < sudokuSize; i++) {
            for (int j = 0; j < sudokuSize; j++) {
                System.out.print(board[i][j]);
                if (j % blockSize == 2) {
                    System.out.print(" ");
                }
            }
            if (i % blockSize == 2) {
                System.out.print("\n");
            }
            System.out.print("\n");
        }
    }

}

