package pl.comp.gui;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * JavaFX App.
 */
public class App extends Application {

    private static Scene scene;

    private static final Logger logger = LoggerFactory.getLogger(App.class);



    @Override
    public void start(Stage stage) throws IOException {
        Locale.setDefault(LocaleController.getLocale());
        scene = new Scene(loadFXML("primary"));
        scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("Lang", LocaleController.getLocale());
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"),
                resourceBundle);
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        logger.info("Start of Application");
        launch();

    }


}