module View {
    requires javafx.controls;
    requires javafx.fxml;

    opens pl.comp to javafx.fxml;
    exports pl.comp;
}