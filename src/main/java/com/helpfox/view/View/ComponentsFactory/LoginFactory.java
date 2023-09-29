package com.helpfox.view.View.ComponentsFactory;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
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
        VBox vbox = LoginFactory.getInstance().verticalBox(root);
        root.setAlignment(vbox, Pos.TOP_LEFT);
        root.getStylesheets().addAll("/com/helpfox/main/Styles/DarkTheme/LoginStylesheet.css");
        root.setId("mainContainer");
        root.getChildren().add(vbox);
    }
    public VBox footerBox(){
        VBox vbox = new VBox();
        Button forgotButton = new Button();
        forgotButton.setText("Esqueceu sua senha?");
        vbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.getChildren().add(forgotButton);
        forgotButton.setId("forgotPswrd");
        return vbox;
    }
    public VBox verticalBox(StackPane root){
        VBox vbox= new VBox();
        vbox.setId("loginContainer");
        vbox.getChildren().addAll(vBox(), footerBox());

        vBox().setAlignment(Pos.TOP_CENTER);

        return vbox;
    }
    public VBox vBox(){
        VBox vBox = new VBox();
        Label label = new Label();
        Image image = new Image(getClass().getResourceAsStream("/com/helpfox/main/Images/barkinsheetlogo.png"));
        ImageView imageView = new ImageView(image);
        imageView.setId("imageBarkin");
        label.setText("FAZER LOGIN");
        imageView.setFitWidth(160.0);
        imageView.setFitHeight(160.0);
        TextField usernameField = new TextField();
        PasswordField passwordField = new PasswordField();
        Button loginButton = new Button();
        loginButton.setText("ENTRAR");
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setSpacing(12.0);
        vBox.getChildren().addAll(imageView, label, usernameField, passwordField, loginButton);
        VBox.setMargin(imageView, new Insets(35.5, 0, 20, 0));
        VBox.setMargin(label, new Insets(0, 0, 15, 0));
        VBox.setMargin(loginButton, new Insets(10.5, 0, 0, 0));
        vBox.setId("vboxForms");
        return vBox;
    }
}
