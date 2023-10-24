package com.helpfox.main.server.database.sqlite;

import com.helpfox.main.server.entity.Gateway;
import com.helpfox.main.server.dao.GatewayDAO;
import com.helpfox.main.server.type.GatewaySearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteGatewayDAO implements GatewayDAO {

    private Connection connection;
    private final List<Gateway> EMPTY = new ArrayList<>();
    private final ArrayList<Gateway> gateways = new ArrayList<>();

    public void setup() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Gateways (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "idDriver INTEGER," +
                    "date INT NOT NULL," +
                    "time INT NOT NULL," +
                    "parked TINYINT NOT NULL," +
                    "CONSTRAINT FK_Gateway_Vehicles FOREIGN KEY (idDriver) REFERENCES Drivers (id));");
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
    public long insertGateway(Gateway gateway) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Gateways VALUES (?,?,?,?,?)");
            stm.setLong(2, gateway.getIdDriver());
            stm.setInt(3, gateway.getDate());
            stm.setInt(4, gateway.getTime());
            stm.setByte(5, gateway.getParked());
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
    public boolean deleteGateway(Gateway gateway) {
        try {
            PreparedStatement stm = connection.prepareStatement("Delete FROM Gateways where id=?");
            stm.setLong(1, gateway.getId());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Gateway> findGatewayByProp(GatewaySearchType searchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (searchType) {
            case ID -> {
                whereClause = "id = ?";
                valueClause = value.toString();
            }
            case IDDRIVER -> {
                whereClause = "idDriver = ?";
                valueClause = value.toString();
            }
            case DATE -> {
                whereClause = "date = ?";
                valueClause = value.toString();
            }
            case TIME -> {
                whereClause = "time = ?";
                valueClause = value.toString();
            }
            case PARKED -> {
                whereClause = "parked = ?";
                valueClause = value.toString();
            }
            default -> System.out.println("Unknown search type");
        }
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Gateways WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
                Gateway gateway = new Gateway();
                gateway.setId(rs.getLong(1));
                gateway.setIdDriver(rs.getLong(2));
                gateway.setDate(rs.getInt(3));
                gateway.setTime(rs.getInt(4));
                gateway.setParked(rs.getByte(5));

                gateways.add(gateway);
            }
            return gateways;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public List<Gateway> findAll() {
        List<Gateway> gateways = new ArrayList<>();
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Gateways ORDER BY id ASC");
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Gateway gateway = new Gateway();
                gateway.setId(rs.getLong(1));
                gateway.setIdDriver(rs.getLong(2));
                gateway.setDate(rs.getInt(3));
                gateway.setTime(rs.getInt(4));
                gateway.setParked(rs.getByte(5));

                gateways.add(gateway);
            }
            return gateways;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}
