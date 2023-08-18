package com.helpfox.main.Controller.viewControllers.LogInControllers;

import com.helpfox.main.Controller.viewControllers.ReusableViewController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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
    private VBox SignInLoaderFXML;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setSignInLoaderFXML();
        buttonBarFXMLLoader();

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
    final void setSignInLoaderFXML(){
        try {
            FXMLLoader SignInLoader = new FXMLLoader(getClass()
                    .getResource("/com/helpfox/main/View/fxml/LogInResources/SignInResources.fxml"));
            SignInLoaderFXML = SignInLoader.load();
            fatherStackPane.getChildren().add(SignInLoaderFXML);
            SignInController signInController = SignInLoader.getController();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}

