package com.helpfox.main.server.dao;

import java.sql.SQLException;

public interface DAO {
    public void setup() throws SQLException;
    public void connect() throws SQLException;
    public void close() throws SQLException;
}
