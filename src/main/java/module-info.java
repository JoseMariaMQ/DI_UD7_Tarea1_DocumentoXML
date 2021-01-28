module DI_UD7_Tarea1 {
    requires java.naming;
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;
    requires java.xml;

    opens controller to javafx.fxml;
    exports controller;
}