package com.helpfox.main.Model.SQLite;

import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import com.helpfox.main.Model.Vehicle.VehicleSearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteVehicleDAO implements VehicleDAO {
    private Connection connection;
    private static final List<Vehicle> EMPTY = new ArrayList<>();
    private static final ArrayList<Vehicle> vehicles = new ArrayList<>();

    @Override
    public void setup() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Vehicles (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "brand VARCHAR(60)," +
                    "color VARCHAR(60)," +
                    "plate VARCHAR(30) NOT NULL," +
                    "observation TEXT)");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() throws SQLException {
        try {
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:vehicles.db");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        try {
            if(connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long insertVehicle(Vehicle vehicle) {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Vehicles VALUES (?,?,?,?,?)");
            stm.setString(2, vehicle.getBrand());
            stm.setString(3, vehicle.getColor());
            stm.setString(4, vehicle.getPlate());
            stm.setString(5, vehicle.getObservation());
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
    public boolean updateVehicle(Vehicle vehicle) {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("UPDATE Vehicles SET brand=?, color=?, plate=?, observation=? WHERE uid=?");
            stm.setString(1, vehicle.getBrand());
            stm.setString(2, vehicle.getColor());
            stm.setString(3, vehicle.getPlate());
            stm.setString(4, vehicle.getObservation());
            stm.setLong(5, vehicle.getUid());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteVehicle(Vehicle vehicle) {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Vehicles WHERE uid=?");
            stm.setLong(1, vehicle.getUid());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Vehicle> findByProp(VehicleSearchType searchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (searchType) {
            case UID -> {
                whereClause = "uid = ?";
                valueClause = value.toString();
            }
            case BRAND -> {
                whereClause = "brand LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case COLOR -> {
                whereClause = "color LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case PLATE -> {
                whereClause = "plate LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case OBSERVATION -> {
                whereClause = "observation LIKE ?";
                valueClause = value.toString();
            }
            default -> System.out.println("Unknown search type");
        }
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Vehicles WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setUid(rs.getLong(1));
                vehicle.setBrand(rs.getString(2));
                vehicle.setColor(rs.getString(3));
                vehicle.setPlate(rs.getString(4));
                vehicle.setObservation(rs.getString(5));

                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public List<Vehicle> findAll() {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Vehicles ORDER BY uid ASC;");
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setUid(rs.getLong(1));
                vehicle.setBrand(rs.getString(2));
                vehicle.setColor(rs.getString(3));
                vehicle.setPlate(rs.getString(4));
                vehicle.setObservation(rs.getString(5));

                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}
