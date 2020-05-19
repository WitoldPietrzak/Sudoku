module ModelProject {
    requires org.apache.commons.lang3;
    requires slf4j.api;
    opens sudoku;
    exports sudoku;
}