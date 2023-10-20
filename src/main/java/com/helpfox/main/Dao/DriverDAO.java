package com.helpfox.main.Dao;

import com.helpfox.main.Entity.Driver;
import com.helpfox.main.Type.DriverSearchType;

import java.util.List;

public interface DriverDAO extends DAO {
    public long insertDriver(Driver driver);
    public boolean updateDriver(Driver driver);
    public  boolean deleteDriver(Driver driver);

    public List<Driver> findByProp(DriverSearchType searchType, Object value);
    public List<Driver> findAll();
}
