package sudoku;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SudokuFieldRemover {
    public void remove(SudokuBoard sudoku, DifficultyLevel difficulty)
            throws CloneNotSupportedException {
        List<Integer> rowNumber = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        List<Integer> colNumber = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8);
        int removedValue = 0;
        for (int i = 0; i < difficulty.getFieldsToRemove(); i++) {
            Collections.shuffle(rowNumber);
            Collections.shuffle(colNumber);


            boolean flag = false;
            for (int j = 0; j < 9; j++) {
                for (int k = 0; k < 9; k++) {

                    if (sudoku.get(rowNumber.get(j), colNumber.get(k)) == 0) {
                        continue;
                    }
                    removedValue = sudoku.get(rowNumber.get(j), colNumber.get(k));
                    sudoku.set(rowNumber.get(j), colNumber.get(k), 0);
                    if (possibleSolutionsCounter(sudoku.clone()) != 1) {
                        sudoku.set(rowNumber.get(j), colNumber.get(k), removedValue);
                    } else {
                        flag = true;
                        break;
                    }

                }
                if (flag) {
                    break;
                }
            }

        }
    }


    private int possibleSolutionsCounter(SudokuBoard sudoku) throws CloneNotSupportedException {
        boolean flag = false;
        int solutions = 0;
        int i = 0;
        int j = 0;
        while (!flag && !(i == 9)) {
            if (sudoku.get(i, j) == 0) {
                flag = true;
                for (int k = 1; k <= 9; k++) {
                    sudoku.set(i, j, k);
                    if (!sudoku.getColumn(j).verify()
                            || !sudoku.getRow(i).verify() || !sudoku.getBox(i, j).verify()) {
                        continue;
                    }
                    solutions += possibleSolutionsCounter(sudoku.clone());
                    if (solutions > 1) {
                        return solutions;
                    }
                }
            }
            j = (j + 1) % sudoku.getSudokuSize();
            if (j == 0) {
                i++;
            }
        }
        if (flag) {
            return solutions;
        }
        if (sudoku.checkBoard()) {
            return 1;
        } else {
            return 0;
        }
    }

}
