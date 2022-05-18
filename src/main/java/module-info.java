module com.wordlehelper.wordlehelper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.wordlehelper.wordlehelper to javafx.fxml;
    exports com.wordlehelper.wordlehelper;
    exports com.wordlehelper.wordlehelper.model;
    opens com.wordlehelper.wordlehelper.model to javafx.fxml;
    exports com.wordlehelper.wordlehelper.model.services;
    opens com.wordlehelper.wordlehelper.model.services to javafx.fxml;
}