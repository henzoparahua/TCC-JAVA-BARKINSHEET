package com.helpfox.main.Dao;

import com.helpfox.main.Entity.Vehicle;
import com.helpfox.main.Type.VehicleSearchType;

import java.util.List;

public interface VehicleDAO extends DAO {
    public long insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(Vehicle vehicle);

    public List<Vehicle> findByProp(VehicleSearchType searchType, Object value);
    public List<Vehicle> findAll();
}
