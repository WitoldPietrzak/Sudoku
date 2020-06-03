package pl.comp.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

class FileSudokuBoardDaoTest {
    private SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard board2;
    private Dao<SudokuBoard> sudokuBoardDao;

    @Test
    void writeReadTest() throws DaoWriteException, DaoReadException, ClassNotFoundException {
        sudokuBoardDao = SudokuBoardDaoFactory.getFileDao("test.txt");
        sudokuBoardDao.write(board1);
        board2 = sudokuBoardDao.read();

        assertEquals(board1, board2);
    }

    @Test
    void readFileExceptionTest() {
        sudokuBoardDao = SudokuBoardDaoFactory.getFileDao("test1.txt");
        assertThrows(DaoReadException.class, () -> sudokuBoardDao.read());


    }

    @Test
    void writeFileExceptionTest() {
        sudokuBoardDao = SudokuBoardDaoFactory.getFileDao("?");
        assertThrows(DaoWriteException.class, () -> sudokuBoardDao.write(board1));
    }
}