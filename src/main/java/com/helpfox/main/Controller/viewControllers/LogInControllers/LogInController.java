package com.helpfox.main.Controller.viewControllers.LogInControllers;

import com.helpfox.main.Controller.viewControllers.ReusableViewController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable {

    @FXML
    private StackPane fatherStackPane;
    @FXML
    private VBox loginContainer;
    @FXML
    private VBox loginContext;
    @FXML
    private Button btReturn;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        buttonBarFXMLLoader();
        try {
            signUp();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    final void buttonBarFXMLLoader(){
        try{
            FXMLLoader buttonsBarLoader = new FXMLLoader(getClass().getResource("/com/helpfox/main/View/fxml/reusableResources/buttonsBar.fxml"));
            HBox child = buttonsBarLoader.load();
            fatherStackPane.getChildren().add(child);
            ReusableViewController reusableViewController = buttonsBarLoader.getController();
            Button btConf = reusableViewController.getBtConf();
            Button btHelp = reusableViewController.getBtHelp();

            btHelp.setVisible(false);
            btHelp.setDisable(true);
            btConf.setDisable(true);
            btConf.setVisible(false);

        } catch(Exception e){
            e.printStackTrace();
        }
    }

    final void signUp() throws IOException {
        FXMLLoader SignUpContextLoader = new FXMLLoader(getClass()
                .getResource("/com/helpfox/main/View/fxml/LogInResources/SignUpResources.fxml"));
        loginContext = SignUpContextLoader.load();

        // Verifica a existencia do node.
        if (loginContainer.getChildren().contains(loginContext)) {
            int indexOfChild = loginContainer.getChildren().indexOf(loginContext);
            loginContainer.getChildren().set(indexOfChild, loginContext);
        } else {
            // Adiciona a children caso não exista.
            loginContainer.getChildren().add(1, loginContext);
        }
            // Controller
        SignUpResources signUpResources = SignUpContextLoader.getController();
    }
    final void signIn() throws IOException {
        FXMLLoader SignUpContextLoader = new FXMLLoader(getClass()
                .getResource("/com/helpfox/main/View/fxml/LogInResources/SignInResources.fxml"));
        loginContext = SignUpContextLoader.load();

        // Verifica a existencia do node.
        if (loginContainer.getChildren().contains(loginContext)) {
            int indexOfChild = loginContainer.getChildren().indexOf(loginContext);
            loginContainer.getChildren().set(indexOfChild, loginContext);
        } else {
            // Adiciona a children caso não exista.
            loginContainer.getChildren().add(1, loginContext);
        }
        // Controller
        SignUpResources signUpResources = SignUpContextLoader.getController();
    }
    @FXML
    void returnOnClick(ActionEvent event) throws IOException {
        loginContainer.getChildren().remove(loginContext);
        signIn();
    }
}

