package com.helpfox.main.server.controller;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UserVehicleForms implements Initializable {



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



    }
    public void close (Button button){
        Stage stage = (Stage) button.getScene().getWindow();
//        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
