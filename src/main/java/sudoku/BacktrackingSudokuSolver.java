package sudoku;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Fills Sudoku Board using Backtracking algorithm.
 */
public class BacktrackingSudokuSolver implements SudokuSolver {
    @Override
    public final void solve(final SudokuBoard sudoku) {
//        int[] firstRow = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        List <Integer> firstRow = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        Collections.shuffle(firstRow);
//        Random random = new Random();
//        for (int i = 0; i < firstRow.length; i++) {
//            int indexSwap = random.nextInt(firstRow.length);
//            int swapBuffor = firstRow[indexSwap];
//            firstRow[indexSwap] = firstRow[i];
//            firstRow[i] = swapBuffor;
//        }
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
