package sudoku;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SudokuField {
    private int value;

    public final int getFieldValue() {
        return value;
    }

    public SudokuField(int value) {
        this.value = value;
    }

    public SudokuField() {
        this.value = 0;
    }

    public final void setFieldValue(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Value", value).toString();
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(value, ((SudokuField) o).value).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(29, 39).append(value).toHashCode();
    }
}
