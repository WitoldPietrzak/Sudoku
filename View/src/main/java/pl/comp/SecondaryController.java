package pl.comp;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;

public class SecondaryController implements Initializable {
    @FXML
    private GridPane grid;

    @FXML
    private void switchToPrimary() throws IOException {

        App.setRoot("primary");

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SudokuBoard sudoku = new SudokuBoard(new BacktrackingSudokuSolver());
        sudoku.solveGame();
        sudoku.testowyWypis();
        for (int i = 0; i < sudoku.getSudokuSize(); i++) {
            for (int j = 0; j < sudoku.getSudokuSize(); j++) {
                TextField textField = new TextField();
                textField.setText(String.valueOf(sudoku.get(i, j)));
                grid.add(textField, j, i);
            }
        }

    }

}