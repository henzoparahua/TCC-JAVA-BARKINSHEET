package com.helpfox.main.Controller;

import com.helpfox.main.App;
import com.helpfox.main.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    @FXML
    public TextField txtUsu;
    @FXML
    public PasswordField txtPswrd;
    @FXML
    public Button btLogin;
    @FXML
    public Button btForgot;
    @FXML
    public AnchorPane rootLogin;

    @FXML
    private Button btSignin;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btSignin.setOnAction(event -> {
            try {
                Scene scene = btSignin.getScene();
                Model.getInstance().getViewFactory().setRoot(scene, "SigninGUI");
            } catch (Exception e){
                e.printStackTrace();
            }
        });

        try {
            Model.getInstance().getViewFactory().createTopbarforLogin(rootLogin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btLogin.setOnAction(event -> {
            try {
                onLogin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }

    private void onLogin() throws IOException {
        Stage stage = (Stage) btLogin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

}