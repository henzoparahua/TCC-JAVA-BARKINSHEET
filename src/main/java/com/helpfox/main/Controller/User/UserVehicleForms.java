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



    private Driver lastDriverConstructor (){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);

        List<Driver> lastDriver = guard.findLast();
        return lastDriver.get(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Driver lastDriver = lastDriverConstructor();
        setName.setText(lastDriver.getNameDriver());

        btConfirmVehicles.setOnAction(event -> {
            if (!setPlate.getText().isEmpty() && !setBrand.getText().isEmpty() && !setColor.getText().isEmpty()){
                VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
                SecurityGuard guard = new SecurityGuard(vehicleDAO);
                guard.addNewVehicle(lastDriver.getUid(), setBrand.getText(),
                        setColor.getText(), setPlate.getText());
                close(btConfirmVehicles);
            }else {
                System.out.println("Preencha os dados novamente, imbecil!");
            }
        });
        btCancelVehicles.setOnAction(event -> {
            close(btCancelVehicles);
        });

    }
    public void close (Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
