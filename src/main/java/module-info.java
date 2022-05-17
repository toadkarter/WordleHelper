module com.wordlehelper.wordlehelper {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;

    opens com.wordlehelper.wordlehelper to javafx.fxml;
    exports com.wordlehelper.wordlehelper;
}