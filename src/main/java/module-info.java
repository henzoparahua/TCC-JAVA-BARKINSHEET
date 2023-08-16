module com.helpfox.main {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.helpfox.main to javafx.fxml;
    exports com.helpfox.main;
}