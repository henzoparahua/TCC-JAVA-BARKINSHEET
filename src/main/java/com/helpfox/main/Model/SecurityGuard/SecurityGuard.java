package com.helpfox.main.Model.SecurityGuard;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Driver.DriverSearchType;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecurityGuard {
    private final DriverDAO driverDAO;
    private final VehicleDAO vehicleDAO;

    public SecurityGuard(DriverDAO driverDAO, VehicleDAO vehicleDAO) {
        this.driverDAO = driverDAO;
        this.vehicleDAO = vehicleDAO;
    }

    public void addNewDriver(String nameDriver, String rg, String phone) {
        Driver driver = new Driver();
        driver.setNameDriver(nameDriver);
        driver.setRg(rg);
        driver.setPhone(phone);
        driver.setPermanence(true);

        try {
            driverDAO.connect();
            driverDAO.insertDriver(driver);
            driverDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewVehicle(String brand, String color, String plate, String observation) {
        Vehicle vehicle = new Vehicle();
        vehicle.setBrand(brand);
        vehicle.setColor(color);
        vehicle.setPlate(plate);
        vehicle.setObservation(observation);

        try {
            vehicleDAO.connect();
            vehicleDAO.insertVehicle(vehicle);
            vehicleDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void checkPermanence(long uid, SetCheckType setCheckType) {
        try {
            driverDAO.connect();
            List<Driver> drivers = driverDAO.findByProp(DriverSearchType.UID, uid);

            switch (setCheckType) {
                case INPUT -> {
                    if (!drivers.isEmpty()) {
                        drivers.get(0).setPermanence(true);
                        driverDAO.updateDriver(drivers.get(0));
                    }
                }
                case OUTPUT -> {
                    if (!drivers.isEmpty()) {
                        drivers.get(0).setPermanence(false);
                        driverDAO.updateDriver(drivers.get(0));
                    }
                }
            }
            driverDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Driver> findAllDrivers() {
        List<Driver> drivers = new ArrayList<>();
        try {
            driverDAO.connect();
            drivers = driverDAO.findAll();
            driverDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    public List<Vehicle> findAllVehicles(){
        return vehicleDAO.findAll();
    }
}
