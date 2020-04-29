package sudoku;

import java.io.*;
import java.util.Collections;
import java.util.List;

public class SudokuRow extends SudokuFieldCollection implements Cloneable, Serializable {
    public SudokuRow(List<SudokuField> fields) {
        super(fields);
    }

    public SudokuRow clone() throws CloneNotSupportedException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SudokuRow) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}

