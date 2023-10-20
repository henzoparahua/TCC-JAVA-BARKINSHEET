package com.helpfox.main.Database.SQLite;

import com.helpfox.main.Entity.Vehicle;
import com.helpfox.main.Dao.VehicleDAO;
import com.helpfox.main.Type.VehicleSearchType;

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
            connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Vehicles (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "idDriver INTEGER, " +
                    "brand VARCHAR(60), " +
                    "color VARCHAR(60), " +
                    "plate VARCHAR(30) NOT NULL, " +
                    "observation VARCHAR(60), " +
                    "CONSTRAINT FK_Vehicles_Drivers FOREIGN KEY (idDriver) REFERENCES Drivers (id));");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void connect() throws SQLException {
        try {
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
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
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Vehicles VALUES (?,?,?,?,?,?)");
            stm.setLong(2, vehicle.getIdDriver());
            stm.setString(3, vehicle.getBrand());
            stm.setString(4, vehicle.getColor());
            stm.setString(5, vehicle.getPlate());
            stm.setString(6, vehicle.getObservation());
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
            PreparedStatement stm = connection.prepareStatement("UPDATE Vehicles SET idDriver=?, brand=?, color=?, plate=?, observation=? WHERE id=?");
            stm.setLong(1, vehicle.getIdDriver());
            stm.setString(2, vehicle.getBrand());
            stm.setString(3, vehicle.getColor());
            stm.setString(4, vehicle.getPlate());
            stm.setString(5, vehicle.getObservation());
            stm.setLong(6, vehicle.getId());

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
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Vehicles WHERE id=?");
            stm.setLong(1, vehicle.getId());
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
            case ID -> {
                whereClause = "id = ?";
                valueClause = value.toString();
            }
            case IDDRIVER->{
                whereClause = "idDriver = ?";
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
                vehicle.setId(rs.getLong(1));
                vehicle.setIdDriver(rs.getLong(2));
                vehicle.setBrand(rs.getString(3));
                vehicle.setColor(rs.getString(4));
                vehicle.setPlate(rs.getString(5));
                vehicle.setObservation(rs.getString(6));

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
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Vehicles ORDER BY id ASC;");
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                Vehicle vehicle = new Vehicle();
                vehicle.setId(rs.getLong(1));
                vehicle.setIdDriver(rs.getLong(2));
                vehicle.setBrand(rs.getString(3));
                vehicle.setColor(rs.getString(4));
                vehicle.setPlate(rs.getString(5));
                vehicle.setObservation(rs.getString(6));

                vehicles.add(vehicle);
            }
            return vehicles;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}
