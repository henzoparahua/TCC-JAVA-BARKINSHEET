package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.io.IOException;
import java.net.URL;
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
        startUpdatingList();
    }

    public synchronized int getValue() {
        return y;
    }

    public synchronized int setValue(int newValue) {
        this.y = newValue;
        return newValue;
    }

    private List<Vehicle> getVehicle (int i){
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(vehicleDAO);
        return guard.findVehicle(i);
    }
    private Driver getDriver ( int i){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO);
        return guard.findAllDrivers().get(i);
    }


    private void startUpdatingList() {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(2);
        executor.scheduleAtFixedRate(this::run, 2, 4, TimeUnit.SECONDS);
    }

    private void forListView() {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO);
        int lng = guard.countProperly();
        Platform.runLater(new Runnable() {
            @Override
            public void run() {

                while (getValue()<lng) {
                    Driver driver = getDriver(getValue());
                    List<Vehicle> vehicles = getVehicle(Math.toIntExact(driver.getUid()));
                    driverListItems.add(new VehicleItem(driver.getNameDriver(), " ", " ", " "));
                    vehicles.clear();
                    setValue(getValue()+1);
                }
                for (int i = 0; i < lng; i++) {
                    Driver driver = getDriver(i);
                    List<Vehicle> vehicles = getVehicle(Math.toIntExact(driver.getUid()));
                    switch (vehicles.size()){
                        case 1:
                            if (!listViewone.getItems().get(i).getPlate_one().equals(vehicles.get(0).getPlate())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(0).getPlate());
                                //     System.out.println("entrei aqui");
                                listViewone.refresh();
                            }
                            // System.out.println("nao entrei aqui");
                            break;
                        case 2:
                            if (!vehicles.get(0).getPlate().equals(listViewone.getItems().get(i).getPlate_one())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(0).getPlate());
                                listViewone.refresh();
                                //     System.out.println("entrei aqui 1:1");

                            }
                            // System.out.println("nao entrei aqui 1:1");

                            if (!vehicles.get(1).getPlate().equals(listViewone.getItems().get(i).getPlate_one())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(1).getPlate());
                                listViewone.refresh();
                                //     System.out.println("entrei aqui 1:2");

                            }
                            // System.out.println("nao entrei aqui 1:2");

                            break;
                        case 3:
                            if (!vehicles.get(0).getPlate().equals(listViewone.getItems().get(i).getPlate_one())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(0).getPlate());
                                listViewone.refresh();
                                //     System.out.println("entrei aqui 2:1");

                            }
                            // System.out.println("nao entrei aqui 2:1");

                            if (!vehicles.get(1).getPlate().equals(listViewone.getItems().get(i).getPlate_one())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(1).getPlate());
                                listViewone.refresh();
                                //     System.out.println("entrei aqui 2:2");

                            }
                            // System.out.println("nao entrei aqui 2:2");

                            if (!vehicles.get(2).getPlate().equals(listViewone.getItems().get(i).getPlate_one())){
                                listViewone.getItems().get(i).setPlate_one(vehicles.get(2).getPlate());
                                listViewone.refresh();
                                //     System.out.println("entrei aqui 2:3");

                            }
                            // System.out.println("nao entrei aqui 2:3");

                            break;
                        default:
                            break;
                    }
                    vehicles.clear();
                }
            }
        });
    }
    private void run () {
        forListView();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        listViewone.setItems(driverListItems);
        listViewone.setCellFactory(driverListItems -> new VehicleCell());
        btAddDriver.setOnAction(event -> {
            btAddDriver.setDisable(true);
            try {
                Model.getInstance().getViewFactory().showAddDriverPopUp();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            btAddDriver.setDisable(false);
        });
        listViewone.setOnMouseClicked(event -> {
            listViewone.setDisable(true);
            if (event.getTarget() instanceof ListCell) {
                VehicleItem selectedItem = listViewone.getSelectionModel().getSelectedItem();
                String name = selectedItem.getName().toString();
                String plate1 = selectedItem.getPlate_one().toString();
                String plate2 = selectedItem.getPlate_two().toString();
                String plate3 = selectedItem.getPlate_three().toString();
                try {
                    Model.getInstance().getViewFactory().showDriverInfo(name, plate1, plate2, plate3);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            listViewone.setDisable(false);
        });
    }


    }
