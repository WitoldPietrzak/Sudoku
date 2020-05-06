module View {
    requires javafx.controls;
    requires javafx.fxml;
    requires ModelProject;

    opens pl.comp to javafx.fxml;
    exports pl.comp;
}