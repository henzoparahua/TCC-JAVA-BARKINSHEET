package com.helpfox.main.Model;

import java.sql.SQLException;

public interface DAO {
    public void setup() throws SQLException;
    public void connect() throws SQLException;
    public void close() throws SQLException;
}
