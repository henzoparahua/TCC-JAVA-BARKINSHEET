package com.helpfox.main.Controller;

import com.helpfox.main.App;
import com.helpfox.main.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginController implements Initializable {
    public TextField txtUsu;
    public PasswordField txtPswrd;
    public Button btLogin;
    public Button btForgot;

    @FXML
    void switchToMenu(ActionEvent event) throws IOException {

    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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