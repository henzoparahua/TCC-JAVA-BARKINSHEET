package com.helpfox.view;

import com.helpfox.view.View.LoginFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        LoginFactory.getInstance().showLogin();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}