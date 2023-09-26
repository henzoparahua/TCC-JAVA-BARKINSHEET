package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class DriverInfo implements Initializable{
    @FXML
    public Label name;
    @FXML
    public RadioButton radioPlate_one;
    @FXML
    public RadioButton radioPlate_two;
    @FXML
    public RadioButton radioPlate_three;
    @FXML
    public AnchorPane anchorMain;
    @FXML
    public Button btGatewayVehicle;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        name.setText(null);
        radioPlate_one.setText(null);
        radioPlate_two.setText(null);
        radioPlate_three.setText(null);

        btGatewayVehicle.setOnAction(event -> {
            DriverDAO driverDAO = new SQLiteDriverDAO();
            VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
            GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
            SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO, gatewayDAO);
            System.out.println(LocalDate.now());
            System.out.println(Time.valueOf(LocalTime.now()));
            if (radioPlate_one.isSelected()) {
                guard.enterGateway(radioPlate_one.getText(), LocalDate.now(), Time.valueOf(LocalTime.now()));
            } else if (radioPlate_two.isSelected()) {

            } else if (radioPlate_three.isSelected()) {

            }else {

            }
        });

    }

    public void name(RadioButton i){



    }
}