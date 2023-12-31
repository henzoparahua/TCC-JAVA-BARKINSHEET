package com.helpfox.main.Controller;

import com.helpfox.main.App;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.SQLite.SQLiteUserDAO;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Validation.SetMsgError;
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

import static com.helpfox.main.Validation.CheckingAccount.*;


public class LoginController implements Initializable {
    @FXML
    public TextField txtEmail;
    @FXML
    public PasswordField txtPswd;
    @FXML
    public Button btLogin;
    @FXML
    public Button btForgot;
    @FXML
    public AnchorPane rootLogin;

    @FXML
    private Button btSignin;

    UserDAO userDAO = new SQLiteUserDAO();

    Office model = new Office(userDAO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btSignin.setOnAction(event -> {
            try {
                Scene scene = btSignin.getScene();
                Model.getInstance().getViewFactory().setRoot(scene, "SigninGUI");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        try {
            Model.getInstance().getViewFactory().createTopbarforLogin(rootLogin);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        btLogin.setOnAction(event -> {
            if (!txtEmail.getText().isEmpty() && !txtPswd.getText().isEmpty()) {
                if (isValidEmail(txtEmail.getText()) && isValidPassword(txtPswd.getText())) {
                    if (model.isValidUser(txtEmail.getText(), txtPswd.getText())) {
                        try {
                            onLogin();
                        } catch (IOException e) {
                            addAlert(SetMsgError.LOGINERROR);
                            throw new RuntimeException(e);
                        }
                    } else {
                        addAlert(SetMsgError.INVALIDLOGIN);
                    }
                } else {
                    if (!isValidEmail(txtEmail.getText())) {
                        addAlert(SetMsgError.INVALIDEMAIL);
                    }

                    if (!isValidPassword(txtPswd.getText())) {
                        addAlert(SetMsgError.INVALIDPASSWORD);
                    }
                }
            } else {
                if (txtEmail.getText().isEmpty()) {
                    addAlert(SetMsgError.EMPTYEMAIL);
                }

                if (txtPswd.getText().isEmpty()) {
                    addAlert(SetMsgError.EMPTYPASSWORD);
                }
            }
        });

    }

    private void onLogin() throws IOException {
        Stage stage = (Stage) btLogin.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
        Model.getInstance().getViewFactory().showClientWindow();
    }

}