package com.helpfox.main.server.dao;

import com.helpfox.main.server.entity.Gateway;
import com.helpfox.main.server.type.GatewaySearchType;

import java.util.List;

public interface GatewayDAO extends DAO {
    public long insertGateway(Gateway gateway);
    public boolean deleteGateway(Gateway gateway);
    public List<Gateway> findGatewayByProp(GatewaySearchType searchType, Object value);
    public List<Gateway> findAll();
}
