package com.helpfox.main.Model.Gateway;

import com.helpfox.main.Model.DAO;

import java.util.List;

public interface GatewayDAO extends DAO {
    public long insertGateway(Gateway gateway);
    public boolean updateGateway(Gateway gateway);
    public List<Gateway> findGatewayByProp(GatewaySearchType searchType, Object value);
    public Integer countOpenGateways();
    public boolean deleteGateway(Gateway gateway);
}
