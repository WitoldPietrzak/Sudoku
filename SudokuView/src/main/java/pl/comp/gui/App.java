package pl.comp.gui;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * JavaFX App.
 */
public class App extends Application {

    private static Scene scene;
    private static String language = "pl";

    private static ResourceBundle resourceBundle;

    public static void setLanguage(String language) {
        App.language = language;
    }

    public static String getLanguage() {
        return language;
    }

    public static ResourceBundle getResourceBundle() {
        return resourceBundle;
    }


    @Override
    public void start(Stage stage) throws IOException {

        scene = new Scene(loadFXML("primary"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        Locale locale = new Locale(language);
        resourceBundle = ResourceBundle.getBundle("Lang",locale);
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"),
                resourceBundle);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}