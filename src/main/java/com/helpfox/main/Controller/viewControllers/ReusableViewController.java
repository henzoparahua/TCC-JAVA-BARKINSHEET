package com.helpfox.main.Controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class ReusableViewController {

    @FXML
    private Button btHelp;

    @FXML
    private Button btConf;

    @FXML
    private Button btMin;

    @FXML
    private Button btExit;

    public Button getBtHelp() {
        return btHelp;
    }

    public Button getBtConf() {
        return btConf;
    }

    public Button getBtMin() {
        return btMin;
    }

    public Button getBtExit() {
        return btExit;
    }

    @FXML
    void Minimize(ActionEvent event) {
        Stage scene = (Stage) ((Node)event.getSource()).getScene().getWindow();
        scene.setIconified(true);

    }

    @FXML
    void Exit(ActionEvent event) {
        System.exit(0);

    }

}
