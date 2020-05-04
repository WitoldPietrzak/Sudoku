package sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

public class SudokuBox extends SudokuFieldCollection implements Cloneable {
    public static final int BOX_SIZE = 3;

    public SudokuBox(List<SudokuField> fields) {
        super(fields);
    }

    public SudokuBox clone() throws CloneNotSupportedException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SudokuBox) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
