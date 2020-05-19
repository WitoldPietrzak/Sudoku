package pl.comp.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import pl.comp.dao.exceptions.DaoReadException;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.SudokuBoard;


public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String fileName;

    public FileSudokuBoardDao(String filename) {
        this.fileName = filename;
    }

    @Override
    public SudokuBoard read() throws DaoReadException {
        SudokuBoard object = null;

        try (FileInputStream fileInput = new FileInputStream(fileName);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            object = (SudokuBoard) objectInput.readObject();
        } catch (ClassNotFoundException | IOException exception) {
            throw new DaoReadException("read");
        }

        return object;
    }

    @Override
    public void write(SudokuBoard obj) throws DaoWriteException {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);
        } catch (IOException exception) {
            throw new DaoWriteException("write", exception);
        }

    }

    @Override
    public void close() {

    }
}
