package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserLeaveDashboard implements Initializable {
    @FXML
    public TextField searchInput;
    @FXML
    public ListView listView;

    ObservableList<VehicleItem> vehicleListItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(vehicleListItems);
        listView.setCellFactory(vehicleListView -> new VehicleCell());
        forListView();

    }

    private void createViewList() throws SQLException {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO, gatewayDAO);

    }

    private void forListView() {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO, gatewayDAO);
            int i = 1;
            int lng = guard.countEmptyGateways();
            while (i < lng) {
                Gateway driver = getDriver(i);
                vehicleListItems.add(new VehicleItem(driver.getNameDriver(), driver.getEntry_date(), driver.getEntry_time(), driver.getPlateDriver()));
                i++;
            }
        }

    private Gateway getDriver(int i) {
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(gatewayDAO);
        return guard.findEmptyGateways(i).get(0);
    }

}