package pl.comp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.stage.FileChooser;
import sudoku.Dao;
import sudoku.DifficultyLevel;
import sudoku.SudokuBoard;
import sudoku.SudokuBoardDaoFactory;


public class PrimaryController implements Initializable {

    private static Property<DifficultyLevel> difficultyLevel = new SimpleObjectProperty<>(DifficultyLevel.Easy);

    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        PrimaryController.difficultyLevel.setValue(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel.getValue();
    }


    @FXML
    ComboBox<DifficultyLevel> comboBox;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBox.getItems().setAll(DifficultyLevel.values());
        comboBox.valueProperty().bindBidirectional(difficultyLevel);

    }

    public void loadSudokuBoard() throws IOException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(comboBox.getScene().getWindow());

        if (file != null) {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> sudokuBoardDao;
            sudokuBoardDao = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath());
            SudokuBoard su = sudokuBoardDao.read();
            new SecondaryController().setSudokuBoard(su);
            App.setRoot("secondary");

        }
    }
}