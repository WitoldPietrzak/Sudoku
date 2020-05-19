package sudoku;

import java.io.Serializable;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import sudoku.exceptions.SudokuFieldException;


public class SudokuField implements Serializable, Cloneable, Comparable<SudokuField> {
    private int value;
    private static final Logger logger = LoggerFactory.getLogger(SudokuField.class);

    public final int getValue() {
        return value;
    }


    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
        this.value = 0;
    }

    public final void setValue(int value) throws SudokuFieldException {
        if (value < 0 || value > 9) {
            logger.warn("Wrong field value!");
            throw new SudokuFieldException("_value");
        }
        //        logger.info("Set field logger");
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
