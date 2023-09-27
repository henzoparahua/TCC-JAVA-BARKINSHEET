package com.helpfox.view.View;

import com.helpfox.view.View.ComponentsFactory.LoginComponents;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class LoginFactory {
    private static LoginFactory model;
    public static synchronized LoginFactory getInstance(){
        if (model == null){
            model = new LoginFactory();
        }
        return model;
    }

    public Node showLogin(){
        Stage stage = new Stage();
        StackPane root = new StackPane();
        Scene scene = new Scene(root, 1052, 620);
        stage.setScene(scene);
        root.getChildren().add(LoginComponents.getInstance().verticalBox());
        stage.show();
        return null;
    }

}
