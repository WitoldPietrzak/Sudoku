package sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SudokuRow extends SudokuFieldCollection implements Cloneable, Serializable {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    public SudokuRow clone() throws CloneNotSupportedException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(this);
                try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    return (SudokuRow) ois.readObject();
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}

