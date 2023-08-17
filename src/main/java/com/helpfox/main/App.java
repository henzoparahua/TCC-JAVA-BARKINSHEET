package com.helpfox.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class App extends Application {

    Image appIcon = new Image(getClass().getResourceAsStream("View/img/barkinsheetlogo-min.png"));
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View/fxml/LogInGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1152, 680);
        stage.getIcons().add(appIcon);
        stage.setTitle("BarkinSheet");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}