package sudoku;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileSudokuBoardDao implements Dao<SudokuBoard>, AutoCloseable {
    private String fileName;

    public FileSudokuBoardDao(String filename) {
        this.fileName = filename;
    }

    @Override
    public SudokuBoard read() {
        SudokuBoard object = null;

        try (FileInputStream fileInput = new FileInputStream(fileName);
             ObjectInputStream objectInput = new ObjectInputStream(fileInput)) {
            object = (SudokuBoard) objectInput.readObject();
        } catch (ClassNotFoundException exception) {
            throw new FileException(exception);
        } catch (IOException exception) {
            throw new FileException(exception);
        }

        return object;
    }

    @Override
    public void write(SudokuBoard obj) {
        try (FileOutputStream fileOut = new FileOutputStream(fileName);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
            objectOut.writeObject(obj);
        } catch (IOException exception) {
            throw new FileException(exception);
        }

    }

    @Override
    public void close() {

    }
}
