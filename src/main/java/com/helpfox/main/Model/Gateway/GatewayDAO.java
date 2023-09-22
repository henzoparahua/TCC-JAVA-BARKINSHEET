package com.helpfox.main.Model.Gateway;

import com.helpfox.main.Model.DAO;

public interface GatewayDAO extends DAO {
    public long insertGateway(Gateway gateway);
    public boolean updateGateway(Gateway gateway);
    public boolean deleteGateway(Gateway gateway);
}
