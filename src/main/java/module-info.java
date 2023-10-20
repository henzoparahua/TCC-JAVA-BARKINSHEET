module com.helpfox.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    //needed for HTTP
    requires unirest.java;
    requires com.google.gson;

    opens com.helpfox.main to javafx.fxml;
    opens com.helpfox.main.Controller to javafx.fxml;
    opens com.helpfox.main.View to javafx.fxml;
    exports com.helpfox.main;
    exports com.helpfox.main.Controller;
    exports com.helpfox.main.View;

    /* Model Related Exports */
    exports com.helpfox.main.Model.User;
    opens com.helpfox.main.Model.User to javafx.fxml;
    exports com.helpfox.main.Model;
    opens com.helpfox.main.Model to javafx.fxml;
    exports com.helpfox.main.Database.SQLite;
    opens com.helpfox.main.Database.SQLite to javafx.fxml;
    exports com.helpfox.main.Http.Validation;
    opens com.helpfox.main.Http.Validation to javafx.fxml;
    exports com.helpfox.main.Dao;
    opens com.helpfox.main.Dao to javafx.fxml;
    exports com.helpfox.main.Service;
    opens com.helpfox.main.Service to javafx.fxml;
    exports com.helpfox.main.Controller.Auth;
    opens com.helpfox.main.Controller.Auth to javafx.fxml;
    exports com.helpfox.main.Entity;
    opens com.helpfox.main.Entity to javafx.fxml;
    exports com.helpfox.main.Type;
    opens com.helpfox.main.Type to javafx.fxml;
    exports com.helpfox.main.Core;
    opens com.helpfox.main.Core to javafx.fxml;
}