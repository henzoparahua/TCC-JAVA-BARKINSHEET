package com.helpfox.main.Dao;

import java.sql.SQLException;

public interface DAO {
    public void setup() throws SQLException;
    public void connect() throws SQLException;
    public void close() throws SQLException;
}
