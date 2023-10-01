package com.helpfox.main.Model.SecurityGuard;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Driver.DriverSearchType;
import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.Gateway.GatewaySearchType;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import com.helpfox.main.Model.Vehicle.VehicleSearchType;

import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SecurityGuard {
    private  DriverDAO driverDAO;
    private  VehicleDAO vehicleDAO;
    private GatewayDAO gatewayDAO;

    public SecurityGuard(DriverDAO driverDAO, VehicleDAO vehicleDAO) {
        this.driverDAO = driverDAO;
        this.vehicleDAO = vehicleDAO;
    }
    public SecurityGuard(DriverDAO driverDAO, VehicleDAO vehicleDAO, GatewayDAO gatewayDAO) {
        this.driverDAO = driverDAO;
        this.vehicleDAO = vehicleDAO;
        this.gatewayDAO = gatewayDAO;
    }
    public SecurityGuard(DriverDAO driverDAO) {
        this.driverDAO = driverDAO;
    }
    public SecurityGuard(VehicleDAO vehicleDAO) {
        this.vehicleDAO = vehicleDAO;
    }
    public SecurityGuard(GatewayDAO gatewayDAO) {
        this.gatewayDAO = gatewayDAO;
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

    public void enterGateway(String plate, String entryDate, String entryTime){
        Gateway gateway = new Gateway();

        try {
            vehicleDAO.connect();
            List<Vehicle> vehicle = vehicleDAO.findByProp(VehicleSearchType.PLATE, plate);
            Long uidDriver = vehicle.get(0).getUidDriver();
            vehicleDAO.close();

            gateway.setUidVehicle(uidDriver);
            gateway.setEntry_date(entryDate);
            gateway.setEntry_time(entryTime);

            gatewayDAO.connect();
            gatewayDAO.insertGateway(gateway);
            gatewayDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void exitGateway(Long uid, String exitTime){
        Gateway gateway = new Gateway();
        gateway.setUid(uid);
        gateway.setExit_time(exitTime);

        try{
            gatewayDAO.connect();
            gatewayDAO.updateGateway(gateway);
            gatewayDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewVehicle(Long uidDriver, String brand, String color, String plate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setUidDriver(uidDriver);
        vehicle.setBrand(brand);
        vehicle.setColor(color);
        vehicle.setPlate(plate);

        try {
            vehicleDAO.connect();
            vehicleDAO.insertVehicle(vehicle);
            vehicleDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Gateway>findEmptyGateways(long uid){
        try {
            gatewayDAO.connect();
            List<Gateway> gateways = gatewayDAO.findGatewayByProp(GatewaySearchType.UID, uid);
            if (gateways.get(0).getExit_time() == null){
                return gateways;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public Integer countEmptyGateways(){
            Integer count = null;
            try {
                gatewayDAO.connect();
                count = gatewayDAO.countOpenGateways();
                gatewayDAO.close();
            } catch (SQLException e){
                e.printStackTrace();
            }
            return count;
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
    public List<Driver> findLast() {
        List<Driver> drivers = new ArrayList<>();
        try {
            driverDAO.connect();
            drivers = driverDAO.findLast();
            driverDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return drivers;
    }
    public int countProperly(){
        int count = 0;
        try {
            driverDAO.connect();
            count = driverDAO.countProperly();
            driverDAO.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return count;
    }
    public List<Vehicle> findAllVehicles(){
        return vehicleDAO.findAll();
    }
    public List<Driver> findDriver(Integer uid){
        List<Driver> driver = null;
        try {
            driverDAO.connect();
            driver = driverDAO.findByProp(DriverSearchType.UID, uid);
            driverDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return driver;
    }
    public List<Vehicle> findVehicle(Integer uid){
        List<Vehicle> vehicles = null;
        try {
            vehicleDAO.connect();
            vehicles = vehicleDAO.findByProp(VehicleSearchType.UIDDRIVER, uid);
            vehicleDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return vehicles;
    }
}
