package com.helpfox.main.Model;

import com.helpfox.main.Entity.Driver;
import com.helpfox.main.Dao.DriverDAO;
import com.helpfox.main.Type.DriverSearchType;
import com.helpfox.main.Entity.Gateway;
import com.helpfox.main.Dao.GatewayDAO;
import com.helpfox.main.Entity.Vehicle;
import com.helpfox.main.Dao.VehicleDAO;
import com.helpfox.main.Type.VehicleSearchType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SecurityGuard {
    private  DriverDAO driverDAO;
    private  VehicleDAO vehicleDAO;
    private GatewayDAO gatewayDAO;

    private long lastDriver;
    private long lastVehicle;

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

    public long addNewDriver(String nameDriver, String rg, String phone) {
        Driver driver = new Driver();
        driver.setName(nameDriver);
        driver.setRg(rg);
        driver.setPhone(phone);
        driver.setPermanence(true);

        try {
            driverDAO.connect();
            return driverDAO.insertDriver(driver);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1L;
    }

    public void enterGateway(String plate, int date, int time, byte parked){
        Gateway gateway = new Gateway();
        byte isParked = 1;

        try {
            vehicleDAO.connect();
            Vehicle vehicle = vehicleDAO.findByProp(VehicleSearchType.PLATE, plate).get(0);
            Long idDriver = vehicle.getIdDriver();
            vehicleDAO.close();

            driverDAO.connect();
            Driver driver = driverDAO.findByProp(DriverSearchType.ID, idDriver).get(0);
            driver.setPermanence(true);
            driverDAO.updateDriver(driver);
            driverDAO.close();

            gateway.setIdDriver(idDriver);
            gateway.setDate(date);
            gateway.setTime(time);
            gateway.setParked(isParked);

            gatewayDAO.connect();
            gatewayDAO.insertGateway(gateway);
            gatewayDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void leaveGateway(String plate, int date, int time, byte parked){
        Gateway gateway = new Gateway();
        byte unparked = 0;

        try{
            vehicleDAO.connect();
            Vehicle vehicle = vehicleDAO.findByProp(VehicleSearchType.PLATE, plate).get(0);
            Long idDriver = vehicle.getIdDriver();
            vehicleDAO.close();

            driverDAO.connect();
            Driver driver = driverDAO.findByProp(DriverSearchType.ID, idDriver).get(0);
            driver.setPermanence(false);
            driverDAO.updateDriver(driver);
            driverDAO.close();

            gateway.setIdDriver(idDriver);
            gateway.setDate(date);
            gateway.setTime(time);
            gateway.setParked(unparked);

            gatewayDAO.connect();
            gatewayDAO.insertGateway(gateway);
            gatewayDAO.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long addNewVehicle(Long idDriver, String brand, String color, String plate) {
        Vehicle vehicle = new Vehicle();
        vehicle.setIdDriver(idDriver);
        vehicle.setBrand(brand);
        vehicle.setColor(color);
        vehicle.setPlate(plate);

        try {
            vehicleDAO.connect();
            return vehicleDAO.insertVehicle(vehicle);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1L;
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
}
