package pl.comp.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.dao.exceptions.DaoReadException;
import sudoku.DifficultyLevel;
import sudoku.SudokuBoard;



public class PrimaryController implements Initializable {

    private static Property<DifficultyLevel> difficultyLevel =
            new SimpleObjectProperty<>(DifficultyLevel.Easy);


    public void setDifficultyLevel(DifficultyLevel difficultyLevel) {
        PrimaryController.difficultyLevel.setValue(difficultyLevel);
    }

    public DifficultyLevel getDifficultyLevel() {
        return difficultyLevel.getValue();
    }


    @FXML
    ComboBox<DifficultyLevel> comboBox;
    @FXML
    public Label labelCreatedBy;
    @FXML
    public Label labelAuthor1;
    @FXML
    public Label labelAuthor2;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ResourceBundle authors = ResourceBundle.getBundle("pl.comp.gui.authors.authors",
                new Locale(App.getLanguage()));
        labelCreatedBy.textProperty().setValue(authors.getString("createdBy"));
        labelAuthor1.textProperty().setValue(authors.getString("author1"));
        labelAuthor2.textProperty().setValue(authors.getString("author2"));
        comboBox.getItems().setAll(DifficultyLevel.values());
        comboBox.valueProperty().bindBidirectional(difficultyLevel);

    }

    public void loadSudokuBoard() throws IOException, DaoReadException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(comboBox.getScene().getWindow());

        if (file != null) {
            Dao<SudokuBoard> sudokuBoardDao;
            sudokuBoardDao = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath());
            SudokuBoard su = sudokuBoardDao.read();
            new SecondaryController().setSudokuBoard(su);
            App.setRoot("secondary");

        }
    }

    public void changeLanguage() throws IOException {
        if (App.getLanguage().matches("pl")) {
            App.setLanguage("en");
        } else {
            App.setLanguage("pl");
        }
        App.setRoot("primary");
    }

}