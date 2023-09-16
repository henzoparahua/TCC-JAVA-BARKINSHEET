package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class UserVehicleForms implements Initializable {

    @FXML public Button btConfirmVehicles;
    @FXML public Button btCancelVehicles;
    @FXML public VBox popupContainer;
    @FXML public TextField setColor;
    @FXML public TextField setBrand;
    @FXML public TextField setPlate;
    @FXML public Label setName;

    DriverDAO driverDAO = new SQLiteDriverDAO();
    VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
    SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);


    private Driver lastDriverConstructor (){
        List<Driver> lastDriver = guard.findLast();
        return lastDriver.get(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Driver lastDriver = lastDriverConstructor();

        try {
            setName.setText(lastDriver.getNameDriver());
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

        btConfirmVehicles.setOnAction(event -> {

            if (!setPlate.getText().isEmpty() && !setBrand.getText().isEmpty() && !setColor.getText().isEmpty()){
            try {
                guard.addNewVehicle(lastDriver.getUid(), setBrand.getText(),
                        setColor.getText(), setPlate.getText());
            }catch (Exception e) {
                throw new RuntimeException(e);
            }

            Stage stage = (Stage) btCancelVehicles.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                System.out.println("Preencha os dados novamente, imbecil!");
            }
        });

        btCancelVehicles.setOnAction(event -> {
            Stage stage = (Stage) btCancelVehicles.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });

    }
}
