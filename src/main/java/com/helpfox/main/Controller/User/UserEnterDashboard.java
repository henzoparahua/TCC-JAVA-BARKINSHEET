package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserEnterDashboard implements Initializable {
    DriverDAO driverDAO = new SQLiteDriverDAO();
    VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
    SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);

    @FXML
    private ListView<VehicleItem> listView;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private TextField searchInput;
    @FXML
    private Button btAddDriver;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        ObservableList<VehicleItem> vehicleListItems = FXCollections.observableArrayList();

        vehicleListItems.addAll(
                new VehicleItem(guard.findLast().get(0).getNameDriver())
        );

        listView.setItems(vehicleListItems);
        listView.setCellFactory(vehicleListView -> new VehicleCell());
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
