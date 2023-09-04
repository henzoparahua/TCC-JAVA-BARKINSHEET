package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import com.helpfox.main.View.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDriverForms implements Initializable {
    @FXML
    public VBox popupContainer;
    @FXML
    private Button btConfirm;
    @FXML
    private Button btCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btCancel.setOnAction(event -> {
            Stage stage = (Stage) btCancel.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });
        btConfirm.setOnAction(event -> {
            Stage stage = (Stage) btConfirm.getScene().getWindow();
            try {
                Model.getInstance().getViewFactory().showAddVehiclePopUp(popupContainer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Model.getInstance().getViewFactory().closeStage(stage);

        });
    }
}
