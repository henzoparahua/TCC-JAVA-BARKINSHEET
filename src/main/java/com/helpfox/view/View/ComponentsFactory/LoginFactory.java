package com.helpfox.view.View.ComponentsFactory;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

public class LoginFactory {
    private static LoginFactory contentLogin;
    public static synchronized LoginFactory getInstance(){
        if (contentLogin == null){
            contentLogin = new LoginFactory();
        }
        return contentLogin;
    }
    public void showLogin(StackPane root){
        VBox vBoxLogin = LoginFactory.getInstance().verticalBox();
        root.setAlignment(vBoxLogin, Pos.TOP_LEFT);
        root.getStylesheets().addAll("/com/helpfox/main/Styles/DarkTheme/LoginStylesheet.css");
        root.setId("mainContainer");
        root.getChildren().add(vBoxLogin);
    }
    public VBox verticalBox(){
        VBox vBox= new VBox();
        vBox.setId("loginContainer");
        Label label = new Label();
        Image image = new Image(getClass().getResourceAsStream("/com/helpfox/main/Images/barkinsheetlogo-min.png"));
        ImageView imageView = new ImageView(image);
        vBox.setAlignment(Pos.TOP_CENTER);
        label.setText("Barkin");
        label.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));

        TextField usernameField = new TextField();

        usernameField.setMaxHeight(30);
        usernameField.setMinHeight(30);
        usernameField.setPrefHeight(30);
        usernameField.setMinWidth(200);
        usernameField.setPrefWidth(200);
        usernameField.setMaxWidth(200);

        PasswordField passwordField = new PasswordField();

        passwordField.setMaxHeight(30);
        passwordField.setMinHeight(30);
        passwordField.setPrefHeight(30);
        passwordField.setMinWidth(200);
        passwordField.setPrefWidth(200);
        passwordField.setMaxWidth(200);


        vBox.getChildren().addAll(label, imageView, usernameField, passwordField);
        return vBox;
    }
}
