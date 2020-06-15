package pl.comp.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.dao.exceptions.DaoReadException;
import sudoku.BacktrackingSudokuSolver;
import sudoku.DifficultyLevel;
import sudoku.SudokuBoard;


public class PrimaryController implements Initializable {

    private static Property<DifficultyLevel> difficultyLevel =
            new SimpleObjectProperty<>(DifficultyLevel.Easy);
    private static final Logger logger = LoggerFactory.getLogger(PrimaryController.class);


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
    public TextField db_filename1;
    @FXML
    private void switchToSecondary() {
        try {
            SudokuBoard su = new SudokuBoard(new BacktrackingSudokuSolver());
            new SecondaryController().setSudokuBoard(su);
            App.setRoot("secondary");
        }catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }
    StringProperty stringProperty = new SimpleStringProperty();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        difficultyLevel.getValue().setLang(LocaleController.getLocale().toString());
        ResourceBundle authors = ResourceBundle.getBundle("pl.comp.gui.authors.authors",
                LocaleController.getLocale());
        labelCreatedBy.textProperty().setValue(authors.getString("createdBy"));
        labelAuthor1.textProperty().setValue(authors.getString("author1"));
        labelAuthor2.textProperty().setValue(authors.getString("author2"));
        comboBox.getItems().setAll(DifficultyLevel.values());
        comboBox.valueProperty().bindBidirectional(difficultyLevel);
        db_filename1.textProperty().bindBidirectional(stringProperty);

    }

    public void loadSudokuBoard() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showOpenDialog(comboBox.getScene().getWindow());

        if (file != null) {
            try {
                Dao<SudokuBoard> sudokuBoardDao;
                sudokuBoardDao = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath());
                SudokuBoard su = sudokuBoardDao.read();
                new SecondaryController().setSudokuBoard(su);
                App.setRoot("secondary");
            }catch(DaoReadException | IOException e) {
                logger.error(e.getLocalizedMessage());
            }


        }
    }

    public void loadSudokuBoardFromDatabase() {
        try {
            if (stringProperty.getValue() != null) {
                Dao<SudokuBoard> sudokuBoardDao;
                sudokuBoardDao = SudokuBoardDaoFactory.getJdbcDao(stringProperty.getValue());
                SudokuBoard su = sudokuBoardDao.read();
                new SecondaryController().setSudokuBoard(su);

                App.setRoot("secondary");

            }
        }catch (IOException | DaoReadException e) {
            logger.error(e.getLocalizedMessage());
        }
    }


    public void changeLanguage() {
        if (LocaleController.getLocale().toString().equals("pl")) {
            LocaleController.setLocale(new Locale("en"));

        } else {
            LocaleController.setLocale(new Locale("pl"));

        }
        Locale.setDefault(LocaleController.getLocale());
        difficultyLevel.getValue().setLang(LocaleController.getLocale().toString());
        try {
            App.setRoot("primary");
        }catch (IOException e) {
            logger.error(e.getLocalizedMessage());
        }
    }

}