package com.helpfox.view.View;

import com.helpfox.view.View.ComponentsFactory.LoginFactory;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ViewFactory {
    private static ViewFactory model;
    public static synchronized ViewFactory getInstance(){
        if (model == null){
            model = new ViewFactory();
        }
        return model;
    }

    public void showLogin(Stage stage){
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1152, 680);
        LoginFactory.getInstance().showLogin(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Barkin - Login");
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }
}
