package com.helpfox.main.Controller;

import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.Office.SetAdminType;
import com.helpfox.main.Model.SQLite.SQLiteUserDAO;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Validation.SetMsgError;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.helpfox.main.Validation.CheckingAccount.*;

public class SigninController implements Initializable {
    @FXML
    private AnchorPane rootSigin;
    @FXML
    private ChoiceBox<SetAdminType> choiceBox;
    @FXML
    private TextField txtName;
    @FXML
    private TextField txtEmail;
    @FXML
    private PasswordField txtPswd;
    @FXML
    private Button btnSignin;
    @FXML
    private Button btnLogin;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        UserDAO userDAO = new SQLiteUserDAO();

        Office model = new Office(userDAO);

        choiceBox.getItems().setAll(SetAdminType.values());
        choiceBox.getSelectionModel().selectFirst();

        btnSignin.setOnAction(event -> {
            if (!txtName.getText().isEmpty() && !txtEmail.getText().isEmpty() && !txtPswd.getText().isEmpty()) {
                if (isValidName(txtName.getText()) && isValidEmail(txtEmail.getText()) && isValidPassword(txtPswd.getText())) {
                    try {
                        SetAdminType selectedValue = choiceBox.getValue();

                        model.addNewUser(txtName.getText(), txtEmail.getText(), txtPswd.getText(), model.createRoleForNewUser(selectedValue));

                        onSignin();
                    } catch (IOException e) {
                        addAlert(SetMsgError.LOGINERROR);
                        throw new RuntimeException(e);
                    }
                } else {
                    if (!isValidName(txtName.getText())) {
                        addAlert(SetMsgError.INVALIDNAME);
                    }

                    if (!isValidEmail(txtEmail.getText())) {
                        addAlert(SetMsgError.INVALIDEMAIL);
                    }

                    if (!isValidPassword(txtPswd.getText())) {
                        addAlert(SetMsgError.INVALIDPASSWORD);
                    }
                }
            } else {
                if (txtName.getText().isEmpty()) {
                    addAlert(SetMsgError.EMPTYNAME);
                }

                if (txtEmail.getText().isEmpty()) {
                    addAlert(SetMsgError.EMPTYEMAIL);
                }

                if (txtPswd.getText().isEmpty()) {
                    addAlert(SetMsgError.EMPTYPASSWORD);
                }
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

    public void onLoginGUI(MouseEvent mouseEvent) {
        try {
            Scene scene = btnLogin.getScene();
            Model.getInstance().getViewFactory().setRoot(scene, "LoginGUI");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
