package sudoku;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FileSudokuBoardDaoTest {
    private SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
    private SudokuBoard board1 = new SudokuBoard(new BacktrackingSudokuSolver());
    private SudokuBoard board2;
    private Dao<SudokuBoard> sudokuBoardDao;

    @Test
    void writeReadTest() {
        sudokuBoardDao = factory.getFileDao("test.txt");
        sudokuBoardDao.write(board1);
        board2 = sudokuBoardDao.read();

        assertEquals(board1, board2);
    }

    @Test
    void readFileExceptionTest() {
        sudokuBoardDao = factory.getFileDao("test1.txt");
        assertThrows(FileException.class, () ->sudokuBoardDao.read());
    }

    @Test
    void writeFileExceptionTest() {
        sudokuBoardDao = factory.getFileDao("?");
        assertThrows(FileException.class, () ->sudokuBoardDao.write(board1));
    }
}