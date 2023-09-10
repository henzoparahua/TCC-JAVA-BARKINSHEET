package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Vehicle.Vehicle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class DriverCell implements Initializable {
    public Label txtThirdPlate;
    public Label txtSecondPlate;
    public Label txtFirstPlate;
    public Label idNum;
    public Label txtNome;
    private final Vehicle vehicle;

    public DriverCell (Vehicle vehicle){
        this.vehicle = vehicle;
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
