package com.helpfox.main.Controller.GUI;

import com.helpfox.main.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class topBar {


    @FXML
    private Button btClose;

    @FXML
    private Button btMinimize;

    @FXML
    private Button btHelp;

    @FXML
    private Button btConfig;

    @FXML
    void Help(ActionEvent event) {

    }

    @FXML
    void Config(ActionEvent event) {

    }

    @FXML
    void Minimize(ActionEvent event) {
        Stage scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene.setIconified(true);
    }

    @FXML
    void Close(ActionEvent event) {
        System.exit(0);
    }


}



