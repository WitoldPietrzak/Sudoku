package sudoku;

import java.io.*;
import java.util.List;

public class SudokuColumn extends SudokuFieldCollection implements Cloneable, Serializable {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }


    public SudokuColumn clone() throws CloneNotSupportedException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(this);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());

            ObjectInputStream ois = new ObjectInputStream(bis);
            return (SudokuColumn) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
