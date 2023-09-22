package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.Driver;
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
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class DriverInfo implements Initializable {
    @FXML public Label driverHeaderName;
    @FXML public RadioButton radioPlate_three;
    @FXML public RadioButton radioPlate_one;
    @FXML public RadioButton radioPlate_two;
    @FXML public AnchorPane anchorMain;

    public void getPlate(ActionEvent event){
        if (radioPlate_one.isSelected()){
            System.out.println(radioPlate_one.getText());
        } else if(radioPlate_two.isSelected()) {
            System.out.println(radioPlate_two.getText());
        }else if (radioPlate_three.isSelected()){
            System.out.println(radioPlate_three.getText());
        }else {
            System.out.println("nothing happens");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Model.getInstance().getViewFactory().createPopUp(anchorMain);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
