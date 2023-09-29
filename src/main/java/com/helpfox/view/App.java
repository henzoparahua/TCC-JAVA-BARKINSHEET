package com.helpfox.view;

import com.helpfox.view.View.ViewFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        ViewFactory.getInstance().showLogin(stage);
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}