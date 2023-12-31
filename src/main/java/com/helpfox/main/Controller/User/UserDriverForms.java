package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import com.helpfox.main.View.ViewFactory;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserDriverForms implements Initializable {
    @FXML
    public VBox popupContainer;
    @FXML
    public TextField setNome;
    @FXML
    public TextArea setObs;
    @FXML
    public TextField setTel;
    @FXML
    public TextField setRG;
    @FXML
    private Button btConfirm;
    @FXML
    private Button btCancel;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btCancel.setOnAction(event -> {
            close(btCancel);
        });

        btConfirm.setOnAction(event -> {
            DriverDAO driverDAO = new SQLiteDriverDAO();
            SecurityGuard guard = new SecurityGuard(driverDAO);

            if (!setNome.getText().isEmpty() && !setTel.getText().isEmpty()){
                guard.addNewDriver(setNome.getText(), setRG.getText(), setTel.getText());
                try {
                    Model.getInstance().getViewFactory().showAddVehiclePopUp(popupContainer);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                close(btConfirm);
                } else {
                System.out.println("Preencha a tabela direito, animal!");
            }
        });
    }
    public void close (Button button){
        Stage stage = (Stage) button.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }
}
