package com.helpfox.main.Database.SQLite;

import com.helpfox.main.Entity.Driver;
import com.helpfox.main.Dao.DriverDAO;
import com.helpfox.main.Type.DriverSearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDriverDAO implements DriverDAO {
    private Connection connection;
    private final List<Driver> EMPTY = new ArrayList<>();
    private final ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public void setup() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Drivers (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(60) NOT NULL," +
                    "rg VARCHAR(13) NULL," +
                    "phone VARCHAR(30) NULL," +
                    "permanence BOOLEAN DEFAULT FALSE);");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long insertDriver(Driver driver) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Drivers VALUES (?,?,?,?,?)");
            stm.setString(2, driver.getName());
            stm.setString(3, driver.getRg());
            stm.setString(4, driver.getPhone());
            stm.setBoolean(5, driver.isPermanence());
            stm.executeUpdate();

            ResultSet rs = connection.prepareStatement("SELECT last_insert_rowid()").executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return -1L;
    }

    @Override
    public boolean updateDriver(Driver driver) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE Drivers SET nameDriver=?, rg=?, phone=?, permanence=? WHERE id=?");
            stm.setString(1, driver.getName());
            stm.setString(2, driver.getRg());
            stm.setString(3, driver.getPhone());
            stm.setBoolean(4, driver.isPermanence());
            stm.setLong(5, driver.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteDriver(Driver driver) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Drivers WHERE id=?");
            stm.setLong(1, driver.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Driver> findByProp(DriverSearchType searchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (searchType) {
            case ID -> {
                whereClause = "id = ?";
                valueClause = value.toString();
            }
            case NAME -> {
                whereClause = "name LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case RG -> {
                whereClause = "rg LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case PHONE -> {
                whereClause = "phone LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case PERMANENCE -> {
                whereClause = "permanence = ?";
                valueClause = value.toString();
            }
            default -> System.out.println("Unknown search type");
        }
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Drivers WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getLong(1));
                driver.setName(rs.getString(2));
                driver.setRg(rs.getString(3));
                driver.setPhone(rs.getString(4));
                driver.setPermanence(rs.getBoolean(5));
                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public List<Driver> findAll() {
        List<Driver> drivers = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Drivers ORDER BY id ASC");
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Driver driver = new Driver();
                driver.setId(rs.getLong(1));
                driver.setName(rs.getString(2));
                driver.setRg(rs.getString(3));
                driver.setPhone(rs.getString(4));
                driver.setPermanence(rs.getBoolean(5));

                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}

