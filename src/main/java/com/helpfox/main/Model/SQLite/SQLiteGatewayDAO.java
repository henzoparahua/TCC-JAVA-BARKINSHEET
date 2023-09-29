package com.helpfox.main.Model.SQLite;

import com.helpfox.main.Model.Gateway.Gateway;
import com.helpfox.main.Model.Gateway.GatewayDAO;

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

}
