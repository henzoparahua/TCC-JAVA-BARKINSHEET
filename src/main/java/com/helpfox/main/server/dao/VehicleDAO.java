package com.helpfox.main.server.dao;

import com.helpfox.main.server.entity.Vehicle;
import com.helpfox.main.server.type.VehicleSearchType;

import java.util.List;

public interface VehicleDAO extends DAO {
    public long insertVehicle(Vehicle vehicle);
    public boolean updateVehicle(Vehicle vehicle);
    public boolean deleteVehicle(Vehicle vehicle);

    public List<Vehicle> findByProp(VehicleSearchType searchType, Object value);
    public List<Vehicle> findAll();
}
