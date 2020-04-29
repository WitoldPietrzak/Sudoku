package sudoku;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Fills Sudoku Board using Backtracking algorithm.
 */
public class BacktrackingSudokuSolver implements SudokuSolver, Serializable {
    @Override
    public final void solve(final SudokuBoard sudoku) {
        List<Integer> firstRow = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(firstRow);
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            sudoku.set(0, i, firstRow.get(i));

        }
        for (int i = 1; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                do {
                    sudoku.set(i, j, (sudoku.get(i, j) + 1)
                            % (sudoku.getSudokuSize() + 1));
                    if (sudoku.get(i, j) == 0) {
                        if (j == 0) {
                            j = sudoku.getSudokuSize() - 2;
                            i--;

                        } else {
                            j -= 2;
                        }
                        break;

                    }

                } while (!(sudoku.getRow(i).verify() && sudoku.getColumn(j).verify()
                        && sudoku.getBox(i, j).verify()));

            }

        }
    }
}
