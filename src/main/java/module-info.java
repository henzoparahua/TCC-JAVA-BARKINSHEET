module com.helpfox.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    //needed for HTTP
    requires unirest.java;

    //needed for JSON
    requires gson;

    opens com.helpfox.main to javafx.fxml;
    opens com.helpfox.main.Controller to javafx.fxml;
    opens com.helpfox.main.Controller.User to javafx.fxml;
    opens com.helpfox.main.View to javafx.fxml;
    exports com.helpfox.main;
    exports com.helpfox.main.Controller;
    exports com.helpfox.main.Controller.User;
    exports com.helpfox.main.View;

    /* Model Related Exports */
    exports com.helpfox.main.Model.User;
    opens com.helpfox.main.Model.User to javafx.fxml;
    exports com.helpfox.main.Model;
    opens com.helpfox.main.Model to javafx.fxml;
    exports com.helpfox.main.Model.SQLite;
    opens com.helpfox.main.Model.SQLite to javafx.fxml;
    exports com.helpfox.main.Model.SecurityGuard;
    opens com.helpfox.main.Model.SecurityGuard to javafx.fxml;
    exports com.helpfox.main.Validation;
    opens com.helpfox.main.Validation to javafx.fxml;
}