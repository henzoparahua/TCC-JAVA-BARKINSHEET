package com.helpfox.main.Model.SQLite;

import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;
import com.helpfox.main.Model.Gateway.GatewaySearchType;
import com.helpfox.main.Model.Vehicle.Vehicle;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteGatewayDAO implements GatewayDAO {

    private Connection connection;
    private final List<Gateway> EMPTY = new ArrayList<>();
    private final ArrayList<Gateway> gateways = new ArrayList<>();

    public void setup() throws SQLException{
        try{
            connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Gateway (" +
                    "    uid INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "    uidVehicle INT NOT NULL, " +
                    "    entry_date DATE NOT NULL, " +
                    "    entry_time TIME NOT NULL, " +
                    "    exit_time TIME, " +
                    "    CONSTRAINT FK_Gateway_Vehicles FOREIGN KEY (uidVehicle) REFERENCES Vehicles (uid));");
            stm.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    @Override
    public void connect() throws SQLException {
        try{
            if (connection == null){
                connection = DriverManager.getConnection("jdbc:sqlite:barkin.db");
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }

    @Override
    public void close() throws SQLException {
        try{
            if(connection!=null){
                connection.close();
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public long insertGateway(Gateway gateway) {
        try{
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Gateway VALUES (?,?,?,?,?)");
            stm.setLong(2, gateway.getUidVehicle());
            stm.setString(3, gateway.getEntry_date());
            stm.setString(4, gateway.getEntry_time());
            stm.executeUpdate();

            ResultSet rs = connection.prepareStatement("SELECT last_insert_rowid()").executeQuery();
            if (rs.next()){
                return rs.getLong(1);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return -1L;
    }

    @Override
    public boolean updateGateway(Gateway gateway) {
        try{
            PreparedStatement stm = connection.prepareStatement("UPDATE Gateway SET uidVehicle=?, entry_date=?, entry_time=?, exit_time=?");
            stm.setLong(2, gateway.getUidVehicle());
            stm.setDate(3, Date.valueOf(gateway.getEntry_date()));
            stm.setString(4,gateway.getEntry_time());
            stm.setString(5,gateway.getExit_time());
            stm.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public List<Gateway> findGatewayByProp(GatewaySearchType searchType, Object value) {
        String whereClause = "";
        String valueClause = "";
        switch (searchType) {
            case UID -> {
                whereClause = "uid = ?";
                valueClause = value.toString();
            }
            case UIDDRIVER->{
                whereClause = "uidDriver = ?";
                valueClause = value.toString();
            }
            case ENTRYDATE -> {
                whereClause = "brand LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case ENTRYTIME -> {
                whereClause = "color LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case EXITTIME -> {
                whereClause = "plate LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            default -> System.out.println("Unknown search type");
        }
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Gateway WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Gateway gateway = new Gateway();
                gateway.setUid(rs.getLong(1));
                gateway.setUidVehicle(rs.getLong(2));
                gateway.setEntry_date(rs.getString(3));
                gateway.setEntry_time(rs.getString(4));
                gateway.setExit_time(rs.getString(5));

                gateways.add(gateway);
            }
            return gateways;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public Integer countOpenGateways() {
        try {
            PreparedStatement stm = connection.prepareStatement(
                    "SELECT COUNT(*) FROM Gateway WHERE exit_time IS NULL");
            ResultSet rs = stm.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteGateway(Gateway gateway) {
        try{
            PreparedStatement stm = connection.prepareStatement("Delete FROM Gateway where uid=?");
            stm.setLong(1, gateway.getUid());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Gateway> findEmptyGateways() {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Gateway WHERE exit_time IS NULL");
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                Gateway gateway = new Gateway();
                gateway.setUid(rs.getLong(1));
                gateway.setUidVehicle(rs.getLong(2));
                gateway.setEntry_date(rs.getString(3));
                gateway.setEntry_time(rs.getString(4));
                gateway.setExit_time(rs.getString(5));

                gateways.add(gateway);
            }
            return gateways;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

}
