module SudokuView {
//    requires SudokuDao;
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;
    requires Dao;
    opens pl.comp.gui to javafx.fxml;
    exports pl.comp.gui;
}