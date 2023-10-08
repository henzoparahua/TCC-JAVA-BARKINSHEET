package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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
    DriverDAO driverDAO = new SQLiteDriverDAO();
    SecurityGuard guard = new SecurityGuard(driverDAO);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btCancel.setOnAction(event -> {
            Stage stage = (Stage) btCancel.getScene().getWindow();
            Model.getInstance().getViewFactory().closeStage(stage);
        });

        btConfirm.setOnAction(event -> {
            if (!setNome.getText().isEmpty() && !setTel.getText().isEmpty()){

            Stage stage = (Stage) btConfirm.getScene().getWindow();
            try {
                Model.getInstance().getViewFactory().showAddVehiclePopUp(popupContainer);
                guard.addNewDriver(setNome.getText(), setRG.getText(), setTel.getText());
                Model.getInstance().getViewFactory().closeStage(stage);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            } else {
                System.out.println("Preencha a tabela direito, animal!");
            }
        });
    }
}
