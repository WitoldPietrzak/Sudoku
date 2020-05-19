package sudoku;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sudoku.exceptions.SudokuCloneException;
import sudoku.exceptions.SudokuFieldCollectionException;


public abstract class SudokuFieldCollection implements Serializable, Cloneable {
    protected List<SudokuField> link;

    public SudokuFieldCollection(List<SudokuField> fields) {
        if (fields.size() != 9) {
            try {
                throw new SudokuFieldCollectionException("_size");
            } catch (SudokuFieldCollectionException e) {
                e.printStackTrace();
            }
        }
        this.link = fields;
    }

    public boolean verify() {
        boolean[] checker = new boolean[link.size() + 1];
        for (SudokuField sudokuField : link) {
            if (checker[sudokuField.getValue()]
                    && (sudokuField.getValue() != 0)) {
                return false;
            }
            checker[sudokuField.getValue()] = true;
        }
        return true;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Fields", link).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuFieldCollection)) {
            return false;
        }
        SudokuFieldCollection that = (SudokuFieldCollection) o;
        return new EqualsBuilder().append(link, that.link).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(39,31).append(link).toHashCode();
    }

    //    public SudokuFieldCollection clone() {
    ////        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
    ////            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
    ////                oos.writeObject(this);
    ////                try (ByteArrayInputStream bis
    // = new ByteArrayInputStream(bos.toByteArray())) {
    ////                    ObjectInputStream ois = new ObjectInputStream(bis);
    ////                    return (SudokuRow) ois.readObject();
    ////                }
    ////            }
    ////
    ////        } catch (IOException | ClassNotFoundException e) {
    ////            return null;
    ////        }
    //        SudokuFieldCollection object = null;
    //        try (ByteArrayOutputStream byteOut = new ByteArrayOutputStream();
    //             ObjectOutputStream objOut = new ObjectOutputStream(byteOut);
    //             ByteArrayInputStream byteIn = new ByteArrayInputStream(byteOut.toByteArray());
    //             ObjectInputStream objIn = new ObjectInputStream(byteIn)) {
    //            objOut.writeObject(this);
    //            object = (SudokuRow) objIn.readObject();
    //        } catch (IOException | ClassNotFoundException e) {
    //            try {
    //                throw new SudokuCloneException("clone error");
    //            } catch (SudokuCloneException ex) {
    //                ex.printStackTrace();
    //            }
    //        }
    //        return object;
    //    }
}
