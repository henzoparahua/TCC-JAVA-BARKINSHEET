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
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserEnterDashboard implements Initializable {

    @FXML
    private ListView<VehicleItem> listView;
    @FXML
    private AnchorPane mainContainer;
    @FXML
    private TextField searchInput;
    @FXML
    private Button btAddDriver;
    ObservableList<VehicleItem> vehicleListItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        createViewList();

        btAddDriver.setOnAction(event -> {
            try {
                onNewDriver();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            newListCell();
        });
    }
    private void onNewDriver () throws IOException {
        Model.getInstance().getViewFactory().showAddDriverPopUp(mainContainer);
    }

    private void createViewList(){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);

        vehicleListItems.add(new VehicleItem(guard.findLast().get(0).getNameDriver()));
        listView.setItems(vehicleListItems);
        listView.setCellFactory(vehicleListView -> new VehicleCell());
    }

    private void newListCell () {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);

        String name = guard.findLast().get(0).getNameDriver();
        String name1 = vehicleListItems.get(vehicleListItems.size() - 1).getName();
        if (!name.equals(name1)) {
            DriverDAO driverDAO1 = new SQLiteDriverDAO();
            SecurityGuard guard1 = new SecurityGuard(driverDAO1, vehicleDAO);
            vehicleListItems.add(new VehicleItem(guard1.findLast().get(0).getNameDriver()));
        }
    }


}
