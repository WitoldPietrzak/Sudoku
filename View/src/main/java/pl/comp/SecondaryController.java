package pl.comp;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.converter.NumberStringConverter;
import sudoku.*;


class ModifiedNumberStringConverter extends NumberStringConverter {
    private final Locale local = Locale.getDefault(Locale.Category.FORMAT);
    private final NumberStringConverter converter = new NumberStringConverter(local);

    @Override
    public Number fromString(String value) {
        if (value.equals("")) {
            value = value.replace("", "0");
        }
        try {
            return converter.fromString(value);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }
        return -1.0;


    }

    @Override
    public String toString(Number value) {

        try {
            if (value.equals(0)) {
                value = null;
            }
            return converter.toString(value);
        } catch (NumberFormatException e) {
            e.getStackTrace();
        }

        return null;
    }

}


public class SecondaryController implements Initializable {


    @FXML
    private GridPane grid;
    @FXML
    private Label msglabel;

    private static SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());

    @FXML
    private void switchToPrimary() throws IOException {

        App.setRoot("primary");

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if (sudoku.checkIfEmpty()) {
            sudoku.solveGame();
            SudokuFieldRemover remover = new SudokuFieldRemover();

            try {
                remover.remove(sudoku, new PrimaryController().getDifficultyLevel());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        List<TextField> textFields = Arrays.asList(new TextField[81]);
        List<IntegerProperty> integerProperties = Arrays.asList(new IntegerProperty[81]);
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                try {
                    integerProperties.set(i * 9 + j, new JavaBeanIntegerPropertyBuilder().bean(sudoku.getField(i, j)).name("value").build());
                    textFields.set(i * 9 + j, new TextField());
                    textFields.get(i * 9 + j).setTextFormatter(new TextFormatter<>(c -> {
                        if (c.isContentChange()) {
                            if (c.getText().matches("[0-9]")) {
                                return c;
                            }
                        }
                        return c;
                    }));

                    textFields.get(i * 9 + j).textProperty().bindBidirectional(integerProperties.get(i * 9 + j), new ModifiedNumberStringConverter());
                    if (textFields.get(i * 9 + j).textProperty().get().matches("[1-9]")) {
                        textFields.get(i * 9 + j).setEditable(false);
                        textFields.get(i * 9 + j).setCursor(Cursor.DEFAULT);
                        textFields.get(i * 9 + j).setStyle("-fx-text-fill: #000180; -fx-background-color: rgb(180,180,180);");
                    }


                    grid.add(textFields.get(i * 9 + j), j, i);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void checkSudoku() {
        if (!sudoku.checkIfSolved()) {
            msglabel.textProperty().setValue("Sudoku nie zostalo uzupelnione");
            msglabel.setStyle("-fx-background-color: yellow;");

        } else if (!sudoku.checkBoard()) {
            msglabel.textProperty().setValue("Sudoku zawiera bledy");
            msglabel.setStyle("-fx-background-color: red;");
        } else {
            msglabel.textProperty().setValue("Sudoku zostalo uzupelnione poprawnie!");
            msglabel.setStyle("-fx-background-color: green;");
        }
    }

    public void saveSudokuBoard() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(grid.getScene().getWindow());

        if (file != null) {
            SudokuBoardDaoFactory factory = new SudokuBoardDaoFactory();
            Dao<SudokuBoard> sudokuBoardDao;
            sudokuBoardDao = SudokuBoardDaoFactory.getFileDao(file.getAbsolutePath());
            sudokuBoardDao.write(sudoku);
        }

    }

    public SudokuBoard getSudokuBoard() {
        return sudoku;
    }
    public void setSudokuBoard(SudokuBoard sudoku) {
        SecondaryController.sudoku = sudoku;
    }
}


