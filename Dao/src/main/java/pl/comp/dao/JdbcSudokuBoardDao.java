package pl.comp.dao;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.SudokuBoard;


public class JdbcSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {

    private String fileName;
    private String databaseUrl = "jdbc:derby://localhost:1527/dbname";
    private static final Logger logger = LoggerFactory.getLogger(JdbcSudokuBoardDao.class);

    public JdbcSudokuBoardDao(String filename) {
        this.fileName = filename;
    }


    @Override
    public void close() throws Exception {

    }

    @Override
    public SudokuBoard read() throws DaoReadException, ClassNotFoundException {
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        SudokuBoard sudokuBoard = null;
        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT sudokuData from sudokus WHERE id = ?")) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                resultSet.next();
                byte[] buf = resultSet.getBytes(1);
                ObjectInputStream ois = null;
                if (buf != null) {
                    ois = new ObjectInputStream(new ByteArrayInputStream(buf));
                    sudokuBoard = (SudokuBoard) ois.readObject();
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("DaoReadException");
            throw new DaoReadException("read");
        }
        return sudokuBoard;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException, ClassNotFoundException {
        //Class.forName("org.apache.derby.jdbc.ClientDriver");
        try (Connection connection = DriverManager.getConnection(databaseUrl);
             PreparedStatement statement =
                     connection.prepareStatement("SELECT sudokuData from sudokus WHERE id = ?")) {
            statement.setString(1, fileName);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (!resultSet.next()) {
                    try (PreparedStatement statement2 =
                                 connection.prepareStatement(
                                         "INSERT INTO sudokus(id,sudokuData) VALUES(?,?)");
                         ByteArrayOutputStream baos =
                                 new ByteArrayOutputStream();
                         ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                        oos.writeObject(obj);
                        InputStream is = new ByteArrayInputStream(baos.toByteArray());
                        statement2.setString(1, fileName);
                        statement2.setBlob(2, is);
                        statement2.executeUpdate();
                    }
                } else {
                    try (PreparedStatement statement2 =
                                 connection.prepareStatement(
                                         "UPDATE sudokus SET sudokuData = ? WHERE id = ? ");
                         ByteArrayOutputStream baos = new ByteArrayOutputStream();
                         ObjectOutputStream oos = new ObjectOutputStream(baos)) {
                        oos.writeObject(obj);
                        InputStream is = new ByteArrayInputStream(baos.toByteArray());
                        statement2.setString(2, fileName);
                        statement2.setBlob(1, is);
                        statement2.executeUpdate();
                    }

                }

            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
            logger.error("DaoReadException");
            throw new DaoWriteException("write", e);
        }
    }
}
