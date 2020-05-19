package sudoku;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import sudoku.exceptions.SudokuFieldException;

public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;

    public final int getValue() {
        return value;
    }


    public SudokuField(int value) {
        this.setValue(value);
    }

    public SudokuField() {
        this.value = 0;
    }

    public final void setValue(int value) {
        if (value < 0 || value > 9) {
            try {
                throw new SudokuFieldException("_value");
            } catch (SudokuFieldException e) {
                e.printStackTrace();
            }
        }
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Value", value).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SudokuField)) {
            return false;
        }
        SudokuField that = (SudokuField) o;
        return new EqualsBuilder().append(value, that.value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(29, 39).append(value).toHashCode();
    }

    public SudokuField clone() throws CloneNotSupportedException {
        return (SudokuField) super.clone();
    }

    public int compareTo(SudokuField f) {
        return Integer.compare(this.value, f.value);
    }
}
