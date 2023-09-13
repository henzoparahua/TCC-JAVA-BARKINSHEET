package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.ResourceBundle;

public class UserVehicleForms implements Initializable {

    @FXML public Button btConfirmVehicles;
    @FXML public Button btCancelVehicles;
    @FXML public VBox popupContainer;

    DriverDAO driverDAO = new SQLiteDriverDAO();
    SecurityGuard guard = new SecurityGuard(driverDAO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        btCancelVehicles.setOnAction(event -> {
            Stage stage = (Stage) btCancelVehicles.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });
        btConfirmVehicles.setOnAction(event -> {
            Stage stage = (Stage) btCancelVehicles.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            System.out.println(guard.findLast());

        });

    }

    private void findLastName(){
        System.out.println(guard.findLast());
    }
}
