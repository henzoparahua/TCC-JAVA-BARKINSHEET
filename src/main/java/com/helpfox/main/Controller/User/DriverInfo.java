package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
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

    public void getPlate(ActionEvent event){
        if (radioPlate_one.isSelected()){
            System.out.println(radioPlate_one.getText());
        } else if(radioPlate_two.isSelected()) {
            System.out.println(radioPlate_two.getText());
        } else if (radioPlate_three.isSelected()){
            System.out.println(radioPlate_three.getText());
        } else {
            System.out.println("nothing happens");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setText(null);
        radioPlate_one.setText(null);
        radioPlate_two.setText(null);
        radioPlate_three.setText(null);
    }
}
