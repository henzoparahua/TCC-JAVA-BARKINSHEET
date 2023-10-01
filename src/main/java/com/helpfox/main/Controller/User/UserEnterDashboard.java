package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class UserEnterDashboard implements Initializable {

    @FXML
    private ListView<VehicleItem> listViewone;
    @FXML
    private Button btAddDriver;
    ObservableList<VehicleItem> driverListItems = FXCollections.observableArrayList();
    private int y = 0;

    public UserEnterDashboard() {
        startUpdatingListy();
    }

    public synchronized int getValue() {
        return y;
    }

    public synchronized void setValue(int newValue) {
        this.y = newValue;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewone.setItems(driverListItems);
        listViewone.setCellFactory(driverListItems -> new VehicleCell());
        btAddDriver.setOnAction(event -> {
            try {
                Model.getInstance().getViewFactory().showAddDriverPopUp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void startUpdatingListy() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        executor.scheduleAtFixedRate(() -> {
            y = forListView(y);
        }, 0, 2, TimeUnit.SECONDS);
        executor.scheduleAtFixedRate(() -> {
            checkList();
        }, 0, 5, TimeUnit.SECONDS);
    }

    private int forListView(int y) {
        try {
            DriverDAO driverDAO = new SQLiteDriverDAO();
            SecurityGuard guard = new SecurityGuard(driverDAO);

            int lng = guard.countProperly();
            while (y < lng) {
                Driver driver = getDriver(y);
                List<Vehicle> vehicles = getVehicle(Math.toIntExact(driver.getUid()));
                String plate1 = " ";
                String plate2 = " ";
                String plate3 = " ";
                if (!vehicles.isEmpty()){
                    plate1 = vehicles.get(0).getPlate();
                }
                if (vehicles.size() >= 2){
                    plate2 = vehicles.get(1).getPlate();
                }
                if (vehicles.size() >= 3){
                    plate3 = vehicles.get(2).getPlate();
                }
                driverListItems.add(new VehicleItem(
                        driver.getNameDriver(),
                        plate1,
                        plate2,
                        plate3));
                y++;
                vehicles.clear();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return y;
    }

    private List<Vehicle> getVehicle(int i) {
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(vehicleDAO);
        return guard.findVehicle(i);
    }
    private Driver getDriver (int i){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO);
        return guard.findAllDrivers().get(i);
    }
    private void checkList(){
        int i = 0;
        while (i != driverListItems.size()){
            Driver driver = getDriver(i);
            List<Vehicle> vehicles = getVehicle(Math.toIntExact(driver.getUid()));
            if (!vehicles.isEmpty()){
                if (!driverListItems.get(i+1).getPlate_one().equals(vehicles.get(0).getPlate())){
                    driverListItems.get(i+1).setPlate_one(vehicles.get(0).getPlate());
                    listViewone.refresh();
                }
            }
            if (vehicles.size() >= 2){
                if (!driverListItems.get(i+1).getPlate_two().equals(vehicles.get(1).getPlate())){
                    driverListItems.get(i+1).setPlate_two(vehicles.get(1).getPlate());
                    listViewone.refresh();
                }
            }
            if (vehicles.size() >= 3){
                if (!driverListItems.get(i+1).getPlate_three().equals(vehicles.get(2).getPlate())){
                    driverListItems.get(i+1).setPlate_three(vehicles.get(2).getPlate());
                    listViewone.refresh();
                }
            }

            vehicles.clear();
            i++;
        }
    }
}