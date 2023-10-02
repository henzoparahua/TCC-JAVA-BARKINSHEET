package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        btGatewayVehicle.setOnAction(event -> {
            DriverDAO driverDAO = new SQLiteDriverDAO();
            VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
            GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
            SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO, gatewayDAO);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String time = String.valueOf(formatter.format(LocalTime.now()));
            String date = String.valueOf(dateTimeFormatter.format(LocalDateTime.now()));


            if (radioPlate_one.isSelected()) {
                guard.enterGateway(radioPlate_one.getText(), date, time);
            } else if (radioPlate_two.isSelected()) {
                guard.enterGateway(radioPlate_two.getText(), date, time);
            } else if (radioPlate_three.isSelected()) {
                guard.enterGateway(radioPlate_three.getText(), date, time);
            } else {

            }
        });

        try {
            Model.getInstance().getViewFactory().createTop(anchorMain);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
