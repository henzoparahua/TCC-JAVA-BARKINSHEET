package com.helpfox.main.Controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class DriverController implements Initializable {
    @FXML
    private Button btAddDriver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btAddDriver.setOnAction(event -> {
            btAddDriver.setDisable(true);
            try {
                Model.getInstance().getViewFactory().showAddDriverPopUp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            btAddDriver.setDisable(false);
        });
    }
}
