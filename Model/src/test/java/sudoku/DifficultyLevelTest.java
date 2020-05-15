package sudoku;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DifficultyLevelTest {

    @Test
    void getFieldsToRemove() {
        DifficultyLevel easy = DifficultyLevel.Easy;
        DifficultyLevel normal = DifficultyLevel.Normal;
        DifficultyLevel hard = DifficultyLevel.Hard;
        assertEquals(easy.getFieldsToRemove(),49);
        assertEquals(normal.getFieldsToRemove(),53);
        assertEquals(hard.getFieldsToRemove(),55);
    }
}