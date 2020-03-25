package sudoku;

import java.util.Random;

/**
 * Fills Sudoku Board using Backtracking algorithm.
 */
public class BacktrackingSudokuSolver implements SudokuSolver {
    @Override
    public final void solve(final SudokuBoard sudoku) {
        Random random = new Random();
        boolean isThere0 = false;
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            sudoku.set(0, i, (random.nextInt(sudoku.getSudokuSize()) + 1));
            if (sudoku.get(0, i) == 0) {
                if (isThere0) {
                    i--;
                    continue;
                } else {
                    isThere0 = true;
                }
            }
            if (!sudoku.checkRow(0)) {
                i--;
            }
        }
        for (int i = 1; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                do {
                    sudoku.set(i, j, (sudoku.get(i, j) + 1)
                            % (sudoku.getSudokuSize() + 1));
                    if (sudoku.get(i, j) == 0) {
                        if (j == 0) {
                            if (i == 0) {
                                sudoku.set(i, j, sudoku.get(i, j) + 1);
                            } else {
                                j = sudoku.getSudokuSize() - 2;
                                i--;
                            }
                        } else {
                            j -= 2;
                        }
                        break;

                    }

                } while (!(sudoku.checkRow(i) && sudoku.checkColumn(j)
                        && sudoku.checkBlock(i, j)));

            }

        }
    }
}
