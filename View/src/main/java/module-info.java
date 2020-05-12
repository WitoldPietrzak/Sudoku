module View {
    requires javafx.controls;
    requires javafx.fxml;
<<<<<<< HEAD
    requires org.apache.commons.lang3;
=======
    requires ModelProject;
>>>>>>> b41a066227446611baef641c472f13e292b1cdb1

    opens pl.comp to javafx.fxml;
    exports pl.comp;
}