package com.helpfox.main.Model.SQLite;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Driver.DriverSearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteDriverDAO implements DriverDAO {
    private Connection connection;
    private static final List<Driver> EMPTY = new ArrayList<>();
    private static final ArrayList<Driver> drivers = new ArrayList<>();

    @Override
    public void setup() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:drivers.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Drivers (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nameDriver VARCHAR(60) NOT NULL," +
                    "rg VARCHAR(13) NOT NULL," +
                    "phone VARCHAR(30))");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() throws SQLException {
        try {
            if (connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:drivers.db");
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
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Drivers VALUES (?,?,?,?)");
            stm.setString(2, driver.getNameDriver());
            stm.setString(3, driver.getRg());
            stm.setString(4, driver.getPhone());
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
            PreparedStatement stm = connection.prepareStatement("UPDATE Drivers SET nameDriver=?, rg=?, phone=? WHERE uid=?");
            stm.setString(1, driver.getNameDriver());
            stm.setString(2, driver.getRg());
            stm.setString(3, driver.getPhone());
            stm.setLong(5, driver.getUid());
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
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Drivers WHERE uid=?");
            stm.setLong(1, driver.getUid());
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
            case UID -> {
                whereClause = "uid = ?";
                valueClause = value.toString();
            }
            case NAMEDRIVER -> {
                whereClause = "nameDriver LIKE ?";
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
            default -> System.out.println("Unknown search type");
        }
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Drivers WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Driver driver = new Driver();
                driver.setUid(rs.getLong(1));
                driver.setNameDriver(rs.getString(2));
                driver.setRg(rs.getString(3));
                driver.setPhone(rs.getString(4));
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
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Drivers ORDER BY uid ASC");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Driver driver = new Driver();
                driver.setUid(rs.getLong(1));
                driver.setNameDriver(rs.getString(2));
                driver.setRg(rs.getString(3));
                driver.setPhone(rs.getString(4));

                drivers.add(driver);
            }
            return drivers;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}
