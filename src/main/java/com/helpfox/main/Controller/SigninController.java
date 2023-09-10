package com.helpfox.main.Controller;

import com.helpfox.main.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SigninController implements Initializable {
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPswd;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnLogin;
    @FXML
    private AnchorPane rootSignin;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnLogin.setOnAction(event -> {
            Scene scene = btnSignin.getScene();
            try {
                Model.getInstance().getViewFactory().setRoot(scene, "LoginGUI");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        try {
            Model.getInstance().getViewFactory().createTopbarforLogin(rootSignin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void onSignin() throws IOException {
        Stage stage = (Stage) btnSignin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }
}
