package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserEnterDashboard implements Initializable {

    @FXML
    private AnchorPane mainContainer;

    @FXML
    private TextField searchInput;

    @FXML
    private Button btAddDriver;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        btAddDriver.setOnAction(event -> {
            try {
                onNewDriver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
    private void onNewDriver () throws IOException {
        Model.getInstance().getViewFactory().showAddDriverPopUp(mainContainer);
    }
}
