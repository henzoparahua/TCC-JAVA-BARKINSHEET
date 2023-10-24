package com.helpfox.main.server.dao;

import com.helpfox.main.server.entity.Driver;
import com.helpfox.main.server.type.DriverSearchType;

import java.util.List;

public interface DriverDAO extends DAO {
    public long insertDriver(Driver driver);
    public boolean updateDriver(Driver driver);
    public  boolean deleteDriver(Driver driver);

    public List<Driver> findByProp(DriverSearchType searchType, Object value);
    public List<Driver> findAll();
}
