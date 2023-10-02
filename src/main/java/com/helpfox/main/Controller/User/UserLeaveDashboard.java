package com.helpfox.main.Controller.User;

import com.helpfox.main.Controller.User.VehicleItem;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserLeaveDashboard implements Initializable {
    @FXML
    private ListView<VehicleItem> listView;

    ObservableList<VehicleItem> vehicleListItems = FXCollections.observableArrayList();

    private int i = 1;

    public UserLeaveDashboard() {
        startUpdatingList();
    }

    public synchronized int getValue() {
        return i;
    }

    public synchronized void setValue(int newValue) {
        this.i = newValue;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listView.setItems(vehicleListItems);
        listView.setCellFactory(vehicleListView -> new VehicleCell());
        }

    private void startUpdatingList() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            i = forListView(i);
        }, 0, 2, TimeUnit.SECONDS);
    }

    private int forListView(int i) {
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(gatewayDAO);

        int lng = guard.countEmptyGateways();
        while (i <= lng) {
            Gateway driver = getDriver(i);
            vehicleListItems.add(new VehicleItem(driver.getNameDriver(), driver.getEntry_date(), driver.getEntry_time(), driver.getPlateDriver()));
            i++;
        }
        return i;
    }

    private Gateway getDriver(int i) {
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(gatewayDAO);
        Gateway driver = guard.findEmptyGateways(i).get(0);
            return driver;

    }
}
