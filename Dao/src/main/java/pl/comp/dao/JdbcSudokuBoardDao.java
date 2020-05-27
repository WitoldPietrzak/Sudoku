package pl.comp.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.SudokuBoard;

import java.sql.*;
import java.util.ResourceBundle;

public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;
    private String URL = "jdbc:derby://localhost/dbname";
    private static final Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    public JdbcSudokuBoardDao(String filename) {
        this.fileName = filename;
    }


    @Override
    public void close() throws Exception {

    }

    @Override
    public SudokuBoard read() throws DaoReadException {
        SudokuBoard sudokuBoard = null;
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement("SELECT sudokuData from sudokus WHERE id = ?")) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                sudokuBoard = (SudokuBoard) resultSet.getObject(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("DaoReadException");
            throw new DaoReadException("read");
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {
        try (Connection connection = DriverManager.getConnection(URL);
             PreparedStatement statement = connection.prepareStatement("SELECT sudokuData from sudokus WHERE id = ?")) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next() == false) {
                    try (PreparedStatement statement2 = connection.prepareStatement("INSERT INTO sudokus(id,sudokuData) VALUES(?,?)")) {
                        statement2.setString(1, fileName);
                        statement2.setObject(2, obj);
                    }
                } else {
                    try (PreparedStatement statement2 = connection.prepareStatement("UPDATE sudokus SET sudokuData = ? WHERE id = ? ")) {
                        statement2.setString(2, fileName);
                        statement2.setObject(1, obj);
                    }

                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("DaoReadException");
            throw new DaoWriteException("write", e);
        }
    }
}
