package com.helpfox.main.View;

import com.helpfox.main.Controller.User.DriverCell;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Vehicle.Vehicle;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class DriverCellFactory extends ListCell<Vehicle>{

    @Override
    protected void updateItem(Vehicle vehicle, boolean empty) {
        super.updateItem(vehicle, empty);
        if (empty){
            setText(null);
            setGraphic(null);
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXMLs/isUser/driverCell.fxml"));
            DriverCell controller = new DriverCell(vehicle);
            loader.setController(controller);
            setText(null);
            try {
                setGraphic(loader.load());
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
