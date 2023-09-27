package com.helpfox.view.View.ComponentsFactory;

import com.helpfox.view.View.LoginFactory;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class LoginComponents {
    private static LoginComponents contentLogin;
    public static synchronized LoginComponents getInstance(){
        if (contentLogin == null){
            contentLogin = new LoginComponents();
        }
        return contentLogin;
    }
    public VBox verticalBox(){
        VBox vBox= new VBox();
        Label label = new Label();
        ImageView imageView = new ImageView();
        TextField passwordField = new TextField();
        vBox.getChildren().addAll(label, imageView, textField(), passwordField);
        return vBox;
    }
    private TextField textField(){
        TextField usernameField = new TextField();
        usernameField.setMinWidth(200.0);
        usernameField.setPrefWidth(200.0);
        usernameField.setMaxWidth(200.0);
        return textField();
    }
}
