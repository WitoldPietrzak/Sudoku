module SudokuView {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires Dao;
    requires slf4j.api;
    opens pl.comp.gui to javafx.fxml;
    exports pl.comp.gui;
}