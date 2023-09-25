package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.RadioButton;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class VehicleCell extends ListCell<VehicleItem> {

    @FXML
    private Label idNum;
    @FXML
    private Label txtNome;
    @FXML
    private Label txtFirstPlate;
    @FXML
    private  Label txtSecondPlate;
    @FXML
    private Label txtThirdPlate;
    private FXMLLoader fxmlLoader;

    @Override
    protected void updateItem(VehicleItem vehicleItem, boolean b) {
        super.updateItem(vehicleItem, b);

        if (b || vehicleItem == null){
            setText(null);
            setGraphic(null);
            setOnMouseClicked(null);
        }else{
            if (fxmlLoader == null){
                FXMLLoader loader = null;
                try {
                    loader = Model.getInstance().getViewFactory().listDriverItem();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                loader.setController(this);
                    try {
                    setGraphic(loader.load());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            idNum.setText(String.valueOf(vehicleItem.getVehicleuid()));
            txtNome.setText(vehicleItem.getName());
            txtFirstPlate.setText(vehicleItem.getPlate_one());
            txtSecondPlate.setText(vehicleItem.getPlate_two());
            txtThirdPlate.setText(vehicleItem.getPlate_three());

        }
    }

}
