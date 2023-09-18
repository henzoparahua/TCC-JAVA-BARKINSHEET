package com.helpfox.main.Model.Driver;

import com.helpfox.main.Model.DAO;

import java.util.List;

public interface DriverDAO extends DAO {
    public long insertDriver(Driver driver);
    public boolean updateDriver(Driver driver);
    public  boolean deleteDriver(Driver driver);

    public List<Driver> findByProp(DriverSearchType searchType, Object value);
    public List<Driver> findAll();
    public List<Driver> findLast();
    public List<Driver> findProperly(int i);
}
