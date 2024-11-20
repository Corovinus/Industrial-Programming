module org.example.texteditor {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires java.desktop;


    opens org.example.texteditor to javafx.fxml;
    exports org.example.texteditor;
}