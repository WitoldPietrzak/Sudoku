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

    //    public SudokuBox clone() {
    //        SudokuBox object = null;
    //        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    //             ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
    //             ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
    //             ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
    //            objOut.writeObject(this);
    //            object = (SudokuBox) objIn.readObject();
    //        } catch (IOException | ClassNotFoundException e) {
    //            try {
    //                throw new SudokuCloneException("clone error");
    //            } catch (SudokuCloneException ex) {
    //                ex.printStackTrace();
    //            }
    //        }
    //        return object;
    //    }
    public SudokuBox clone() throws CloneNotSupportedException {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(this);
                try (ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray())) {
                    ObjectInputStream ois = new ObjectInputStream(bis);
                    return (SudokuBox) ois.readObject();
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            return null;
        }
    }
}
