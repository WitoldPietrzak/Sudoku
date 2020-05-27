package pl.comp.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.adapter.JavaBeanIntegerPropertyBuilder;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import pl.comp.dao.Dao;
import pl.comp.dao.SudokuBoardDaoFactory;
import pl.comp.dao.exceptions.DaoWriteException;
import sudoku.BacktrackingSudokuSolver;
import sudoku.SudokuBoard;
import sudoku.SudokuFieldRemover;


public class SecondaryController implements Initializable {



    @FXML
    private GridPane grid;
    @FXML
    private GridPane grid11;
    @FXML
    private GridPane grid12;
    @FXML
    private GridPane grid13;
    @FXML
    private GridPane grid21;
    @FXML
    private GridPane grid22;
    @FXML
    private GridPane grid23;
    @FXML
    private GridPane grid31;
    @FXML
    private GridPane grid32;
    @FXML
    private GridPane grid33;
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
                    integerProperties.set(i * 9 + j,
                            new JavaBeanIntegerPropertyBuilder().bean(
                                    sudoku.getField(i, j)).name("value").build());
                    textFields.set(i * 9 + j, new TextField());
                    textFields.get(i * 9 + j).setTextFormatter(new TextFormatter<>(c -> {
                        if (c.isContentChange()) {
                            if (c.getText().matches("[1-9]")) {
                                return c;
                            }
                        }
                        c.setText("");
                        return c;
                    }));

                    textFields.get(i * 9 + j).setMaxSize(50,50);
                    textFields.get(i * 9 + j).setPrefSize(50,50);
                    textFields.get(i * 9 + j).setMinSize(50,50);
                    textFields.get(i * 9 + j).textProperty().bindBidirectional(
                            integerProperties.get(i * 9 + j), new ModifiedNumberStringConverter());
                    textFields.get(i * 9 + j).setId("field" + i + j);

                    if (textFields.get(i * 9 + j).textProperty().get().matches("[1-9]")) {
                        textFields.get(i * 9 + j).setEditable(false);
                        textFields.get(i * 9 + j).setCursor(Cursor.DEFAULT);
                        textFields.get(i * 9 + j).setStyle("-fx-text-fill: #000180;"
                                + " -fx-background-color: rgb(180,180,180);");
                    }

                    if(i/3==0 ){
                        if(j/3==0){
                            grid11.add(textFields.get(i * 9 + j), j, i);
                        }
                        if(j/3==1){
                            grid12.add(textFields.get(i * 9 + j), j-3, i);
                        }
                        if(j/3==2){
                            grid13.add(textFields.get(i * 9 + j), j-6, i);
                        }
                    }
                    if(i/3==1 ){
                        if(j/3==0){
                            grid21.add(textFields.get(i * 9 + j), j, i-3);
                        }
                        if(j/3==1){
                            grid22.add(textFields.get(i * 9 + j), j-3, i-3);
                        }
                        if(j/3==2){
                            grid23.add(textFields.get(i * 9 + j), j-6, i-3);
                        }
                    }
                    if(i/3==2 ){
                        if(j/3==0){
                            grid31.add(textFields.get(i * 9 + j), j, i-6);
                        }
                        if(j/3==1){
                            grid32.add(textFields.get(i * 9 + j), j-3, i-6);
                        }
                        if(j/3==2){
                            grid33.add(textFields.get(i * 9 + j), j-6, i-6);
                        }
                    }
                    //grid.add(textFields.get(i * 9 + j), j, i);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
            }
        }


        grid.setHgap(3); //horizontal gap in pixels => that's what you are asking for
        grid.setVgap(3); //vertical gap in pixels
    }


    public void checkSudoku() {
        if (!sudoku.checkIfSolved()) {
            msglabel.textProperty().setValue(App.getResourceBundle().getString("message1"));
            msglabel.setStyle("-fx-background-color: yellow;");

        } else if (!sudoku.checkBoard()) {
            msglabel.textProperty().setValue(App.getResourceBundle().getString("message2"));
            msglabel.setStyle("-fx-background-color: red;");
        } else {
            msglabel.textProperty().setValue(App.getResourceBundle().getString("message3"));
            msglabel.setStyle("-fx-background-color: green;");
        }
    }

    public void saveSudokuBoard() throws DaoWriteException, ClassNotFoundException {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)",
                "*.txt");
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(grid.getScene().getWindow());

        if (file != null) {

            Dao<SudokuBoard> sudokuBoardDao = SudokuBoardDaoFactory.getFileDao(
                    file.getAbsolutePath());
            sudokuBoardDao.write(sudoku);
        }

    }

    public void saveSudokuBoardToDatabase() throws DaoWriteException, ClassNotFoundException {
        String file = "Nazwa";
        if (file != null) {

            Dao<SudokuBoard> sudokuBoardDao = SudokuBoardDaoFactory.getJdbcDao(file);
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


