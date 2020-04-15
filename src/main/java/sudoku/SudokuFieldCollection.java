package sudoku;

import java.util.List;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;


public abstract class SudokuFieldCollection {
    private List<SudokuField> link;

    public SudokuFieldCollection(List<SudokuField> fields) {
        this.link = fields;
    }

    public boolean verify() {
        boolean[] checker = new boolean[link.size() + 1];
        for (SudokuField sudokuField : link) {
            if (checker[sudokuField.getFieldValue()]
                    && (sudokuField.getFieldValue() != 0)) {
                return false;
            }
            checker[sudokuField.getFieldValue()] = true;
        }
        return true;

    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("Fields", link).toString();
    }

    @Override
    public boolean equals(Object o) {
        return new EqualsBuilder().append(link, ((SudokuFieldCollection) o).link).isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(39,31).append(link).toHashCode();
    }
}
