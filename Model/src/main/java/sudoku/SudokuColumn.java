package sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;

public class SudokuColumn extends SudokuFieldCollection implements Cloneable, Serializable {
    public SudokuColumn(List<SudokuField> fields) {
        super(fields);
    }

    //    public SudokuColumn clone() {
    //        SudokuColumn object = null;
    //        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    //             ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
    //             ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
    //             ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
    //            objOut.writeObject(this);
    //            object = (SudokuColumn) objIn.readObject();
    //        } catch (IOException | ClassNotFoundException e) {
    //            try {
    //                throw new SudokuCloneException("clone error");
    //            } catch (SudokuCloneException ex) {
    //                ex.printStackTrace();
    //            }
    //        }
    //        return object;
    //    }
    public SudokuColumn clone() throws CloneNotSupportedException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(this);
                try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    return (SudokuColumn) ois.readObject();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
