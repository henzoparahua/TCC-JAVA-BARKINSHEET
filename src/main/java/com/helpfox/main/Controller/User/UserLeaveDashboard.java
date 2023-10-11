package com.helpfox.main.Controller.User;

import com.helpfox.main.Controller.User.VehicleItem;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.application.Platform;
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

    private int i = 0;

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
        executor.scheduleAtFixedRate(this::forListView, 0, 2, TimeUnit.SECONDS);
    }

    private void forListView() {
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(gatewayDAO);
        int lng = guard.countEmptyGateways();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                while (getValue() < lng) {
                    Gateway gateway = getGateway(getValue());
                    Vehicle vehicle = getVehicle((int) gateway.getUidVehicle());
                    String name = getDriver(vehicle);
                    vehicleListItems.add(new VehicleItem(getValue(), name, gateway.getEntry_date(), gateway.getEntry_time(), vehicle.getPlate()));
                    setValue(getValue()+1);
                }
            }
        });

    }

    private Gateway getGateway(int i) {
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();
        SecurityGuard guard = new SecurityGuard(gatewayDAO);
        return guard.findEmptyGateways().get(i);
    }
    private Vehicle getVehicle(int uid){
        Vehicle vehicles = null;
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(vehicleDAO);
        try {
            vehicles = guard.findVehicle(uid).get(0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
    private String getDriver(Vehicle vehicle){
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        DriverDAO driverDAO = new SQLiteDriverDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);
        try {
        return guard.findDriverName(vehicle.getPlate());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
