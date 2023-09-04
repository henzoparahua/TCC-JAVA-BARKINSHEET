package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserVehicleForms implements Initializable {

    @FXML public Button btConfirm;
    @FXML public Button btCancel;
    @FXML public VBox popupContainer;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btCancel.setOnAction(event -> {
            Stage stage = (Stage) btCancel.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });
        btConfirm.setOnAction(event -> {
            Stage stage = (Stage) btCancel.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });

    }
}
