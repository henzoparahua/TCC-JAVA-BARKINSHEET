package com.helpfox.main.Dao;

import com.helpfox.main.Entity.Gateway;
import com.helpfox.main.Type.GatewaySearchType;

import java.util.List;

public interface GatewayDAO extends DAO {
    public long insertGateway(Gateway gateway);
    public boolean deleteGateway(Gateway gateway);
    public List<Gateway> findGatewayByProp(GatewaySearchType searchType, Object value);
    public List<Gateway> findAll();
}
