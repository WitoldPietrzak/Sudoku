module View {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.apache.commons.lang3;

    opens pl.comp to javafx.fxml;
    exports pl.comp;
}