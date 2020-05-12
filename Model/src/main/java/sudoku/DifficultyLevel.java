package sudoku;

public enum DifficultyLevel {
    Easy,Normal,Hard;


    private int fieldsToRemove;
    static {
        Easy.fieldsToRemove = 49;
        Normal.fieldsToRemove = 53;
        Hard.fieldsToRemove = 55;
    }

    public int getFieldsToRemove() {
        return fieldsToRemove;
    }
}
