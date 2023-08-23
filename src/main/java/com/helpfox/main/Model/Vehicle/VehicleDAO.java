package com.helpfox.main.Model.Vehicle;

import com.helpfox.main.Model.DAO;

import java.util.List;

public interface VehicleDAO extends DAO {
    public String insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(Vehicle vehicle);

    public List<Vehicle> findByProp(VehicleSearchType searchType, Object value);
    public List<Vehicle> findAll();
}
