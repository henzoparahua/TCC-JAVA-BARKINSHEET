package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Driver.Driver;
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
import java.util.List;
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
        listView.setItems(vehicleListItems);
        listView.setCellFactory(vehicleListView -> new VehicleCell());

        try {
            createViewList();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

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

    private void createViewList() throws SQLException {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);
        if (!guard.findLast().isEmpty()){
            forListView();
        }
    }
    private void forListView() {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO);

        List<Driver> lastDrivers = guard.findLast();
        if (!lastDrivers.isEmpty()) {
            int name = (int) lastDrivers.get(0).getUid();
            if (name != 0) {
                for (int i = 1; i < name; i++) {
                    vehicleListItems.add(new VehicleItem(getNameDrivers(i), getPlates(i, 0), getPlates(i, 1), getPlates(i, 2)));
                }
            }
        }
    }


    private void newListCell () {
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);

        String name = guard.findLast().get(0).getNameDriver();
        if (vehicleListItems.isEmpty()){
                DriverDAO driverDAO1 = new SQLiteDriverDAO();
                SecurityGuard guard1 = new SecurityGuard(driverDAO1, vehicleDAO);

                long uidDriver = guard1.findLast().get(0).getUid();
                vehicleListItems.add(new VehicleItem(getNameDrivers((int) uidDriver), getPlates((int) uidDriver, 0), getPlates((int) uidDriver, 1), getPlates((int) uidDriver, 2)));
        } else {
            String name1 = vehicleListItems.get(vehicleListItems.size() - 1).getName();

            if (!name.equals(name1)) {
                DriverDAO driverDAO1 = new SQLiteDriverDAO();
                SecurityGuard guard1 = new SecurityGuard(driverDAO1, vehicleDAO);
                long uidDriver = guard1.findLast().get(0).getUid();
                vehicleListItems.add(new VehicleItem(getNameDrivers((int) uidDriver), getPlates((int) uidDriver, 0), getPlates((int) uidDriver, 1), getPlates((int) uidDriver, 2)));
            }
        }
    }


    private String getPlates(int i, int e) {
        Integer y = Count(i);
        if (y == null || e >= y) {
            return " ";
        } else {
            DriverDAO driverDAO1 = new SQLiteDriverDAO();
            VehicleDAO vehicleDAO1 = new SQLiteVehicleDAO();
            SecurityGuard guard1 = new SecurityGuard(driverDAO1, vehicleDAO1);
            List<Driver> drivers = guard1.findProperly(i);

            if (drivers.size() > e) {
                return drivers.get(e).getPlate();
            } else {
                return " ";
            }
        }
    }

    private Integer Count(int i){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);
        return guard.countProperly(i);

    }
    private String getNameDrivers(int i){
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        SecurityGuard guard = new SecurityGuard(driverDAO, vehicleDAO);
        return guard.findProperly(i).get(0).getNameDriver();
    }
}

