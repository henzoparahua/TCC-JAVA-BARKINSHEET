package com.helpfox.main.server.controller;

import com.helpfox.main.server.dao.DriverDAO;
import com.helpfox.main.server.database.sqlite.SQLiteVehicleDAO;
import com.helpfox.main.server.dao.VehicleDAO;
import com.helpfox.main.server.database.sqlite.SQLiteDriverDAO;
import com.helpfox.main.server.model.SecurityGuard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserDriverForms implements Initializable {
    @FXML
    public VBox popupContainer;
    @FXML
    public TextField txtName;
    @FXML
    public TextField txtRg;
    @FXML
    public TextField txtPhone;
    @FXML
    private Button btnConfirm;
    @FXML
    private Button btnCancel;
    @FXML
    public Button btnConfirmVehicles;
    @FXML
    public Button btnCancelVehicles;
    @FXML
    public TextField setColor;
    @FXML
    public TextField setBrand;
    @FXML
    public TextField setPlate;
    @FXML
    public Label setName;

    long lastDriver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btnCancel.setOnAction(event -> {
            close(btnCancel);
        });

        btnConfirm.setOnAction(event -> {
            DriverDAO driverDAO = new SQLiteDriverDAO();
            SecurityGuard guard = new SecurityGuard(driverDAO);

            if (!txtName.getText().isEmpty()) {
                lastDriver = guard.addNewDriver(txtName.getText(), txtRg.getText(), txtPhone.getText());
//                try {
//                    Model.getInstance().getViewFactory().showAddVehiclePopUp(popupContainer);
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
                close(btnConfirm);
            } else {
                System.out.println("Nome do motorista é obrigatório.");
            }
        });


        btnConfirmVehicles.setOnAction(event -> {
            if (!setPlate.getText().isEmpty() && !setBrand.getText().isEmpty() && !setColor.getText().isEmpty()) {
                VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
                SecurityGuard guard = new SecurityGuard(vehicleDAO);
                guard.addNewVehicle(lastDriver, setBrand.getText(),
                        setColor.getText(), setPlate.getText());
                close(btnConfirmVehicles);
            } else {
                System.out.println("Preencha os dados novamente, imbecil!");
            }
        });
        btnCancelVehicles.setOnAction(event -> {
            close(btnCancelVehicles);
        });
    }

    public void close(Button button) {
        Stage stage = (Stage) button.getScene().getWindow();
//        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
