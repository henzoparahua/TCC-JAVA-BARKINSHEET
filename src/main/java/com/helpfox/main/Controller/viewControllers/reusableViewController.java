package com.helpfox.main.Controller.viewControllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class reusableViewController {

    @FXML
    private Button btMin;

    @FXML
    private Button btExit;

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
