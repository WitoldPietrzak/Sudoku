package pl.comp.dao;

import org.junit.jupiter.api.Test;
import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

import static org.junit.jupiter.api.Assertions.*;

class JdbcSudokuBoardDaoTest {

    @Test
    void read() {
    }

    @Test
    void write() throws DaoWriteException, ClassNotFoundException, DaoReadException {
        JdbcSudokuBoardDao jdbcSudokuBoardDao = new JdbcSudokuBoardDao("plik");
        SudokuBoard sudokuBoard = new SudokuBoard(new BacktrackingSudokuSolver());
        sudokuBoard.solveGame();
        jdbcSudokuBoardDao.write(sudokuBoard);
        SudokuBoard sudokuBoard1 = jdbcSudokuBoardDao.read();
        assertEquals(sudokuBoard, sudokuBoard1);
    }
}