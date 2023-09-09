package com.helpfox.main.Controller;

import com.helpfox.main.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.helpfox.main.Validation.CheckingAccount.isValidEmail;
import static com.helpfox.main.Validation.CheckingAccount.isValidPassword;

public class SigninController implements Initializable {
    @FXML
    private AnchorPane rootSigin;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPswd;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnLogin;

    String invalidEmailMsg = "Por favor, coloque um email válido.";
    String invalidPasswordMsg = "Por favor, coloque uma senha com números, letras maiúsculas e minúsculas.";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btnSignin.setOnAction(event -> {
            try {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                if(txtEmail.getText().trim().isEmpty()) {
                    alert.setContentText("Por favor, preencha o email.");
                }

                if(txtPswd.getText().trim().isEmpty()) {
                    alert.setContentText("Por favor, preencha a senha.");
                }

                if(!isValidEmail(txtEmail.getText())) {
                    alert.setContentText(invalidEmailMsg);
                    alert.showAndWait();
                }
                if(!isValidPassword(txtPswd.getText())) {
                    alert.setContentText(invalidPasswordMsg);
                    alert.showAndWait();
                }
                onSignin();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

        btnLogin.setOnAction(event -> {
            try {
                Scene scene = btnLogin.getScene();
                Model.getInstance().getViewFactory().setRoot(scene, "LoginGUI");
            } catch (Exception e){

            }
        });

        try {
            Model.getInstance().getViewFactory().createTopbarforLogin(rootSigin);
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
