module com.helpfox.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.xerial.sqlitejdbc;

    //needed for HTTP
    //requires unirest.java;
    requires com.google.gson;
    requires commons.collections4;

    opens com.helpfox.main.server.controller to javafx.fxml;
    exports com.helpfox.main.server.controller;
    exports com.helpfox.main.server.model;
    opens com.helpfox.main.server.model to javafx.fxml;
    exports com.helpfox.main.server.database.sqlite;
    opens com.helpfox.main.server.database.sqlite to javafx.fxml;
    exports com.helpfox.main.server.http.Validation;
    opens com.helpfox.main.server.http.Validation to javafx.fxml;
    exports com.helpfox.main.server.dao;
    opens com.helpfox.main.server.dao to javafx.fxml;
    exports com.helpfox.main.server.controller.auth;
    opens com.helpfox.main.server.controller.auth to javafx.fxml;
    exports com.helpfox.main.server.entity;
    opens com.helpfox.main.server.entity to javafx.fxml;
    exports com.helpfox.main.server.type;
    opens com.helpfox.main.server.type to javafx.fxml;
    exports com.helpfox.main.core;
    opens com.helpfox.main.core to javafx.fxml;
    exports com.helpfox.main.core.framework;
    opens com.helpfox.main.core.framework to javafx.fxml;
    exports com.helpfox.main.core.manager;
    opens com.helpfox.main.core.manager to javafx.fxml;
    exports com.helpfox.main.core.components.component;
    opens com.helpfox.main.core.components.component to javafx.fxml;
    exports com.helpfox.main.core.components.layout;
    opens com.helpfox.main.core.components.layout to javafx.fxml;
    exports com.helpfox.main.app;
    opens com.helpfox.main.app to javafx.fxml;
    opens com.helpfox.main.app.fragments to javafx.fxml;
    opens com.helpfox.main.app.tableview to javafx.base;
    opens com.helpfox.main.core.components.component.flexbox to javafx.fxml;
}