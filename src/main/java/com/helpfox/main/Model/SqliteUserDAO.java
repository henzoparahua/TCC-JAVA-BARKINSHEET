package com.helpfox.main.Model;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqliteUserDAO implements UserDAO {
    private Connection connection;
    private static final List<User> EMPTY = new ArrayList<>();
    @Override
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db;create=true");
        PreparedStatement stm = connection.prepareStatement("CREATE TABLE Users (" +
                "uid BIGINT PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(60) NOT NULL," +
                "email VARCHAR(60) NOT NULL," +
                "password VARCHAR(30) NOT NULL," +
                "isAdmin BOOLEAN);");
        stm.executeUpdate();
    }

    @Override
    public void connect() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db");
    }

    @Override
    public void close() throws Exception {
        connection.close();
        try {
            DriverManager.getConnection("jdbc:sqlite:users.db;shutdown=true");
        } catch (Exception e) {}
    }

    @Override
    public long insertUser(User user) throws Exception {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?);");
            stm.setString(2, user.getName());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setBoolean(5, user.getIsAdmin());
            stm.executeUpdate();

            ResultSet rs = connection.prepareStatement("SELECT last_insert_rowid();").executeQuery();
            if (rs.next()) {
                return rs.getLong(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }

        return -1L;
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("UPDATE Users SET name=?, email=?, password=?, isAdmin=? WHERE uid=?;");
            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setBoolean(4, user.getIsAdmin());
            stm.setLong(5, user.getUid());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) throws Exception {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Users WHERE uid=?;");
            stm.setLong(1, user.getUid());
            stm.executeUpdate();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return false;
    }

    @Override
    public List<User> findByProp(UserSearchType searchType, Object value) throws Exception {
        ArrayList<User> result = new ArrayList<>();
        String whereClause = "";
        String valueClause = "";
        switch (searchType) {
            case UID -> {
                whereClause = "uid = ?";
                valueClause = value.toString();
            }
            case NAME -> {
                whereClause = "name LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case EMAIL -> {
                whereClause = "email LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case PASSWORD -> {
                whereClause = "password LIKE ?";
                valueClause = "%" + value.toString() + "%";
            }
            case ISADMIN -> {
                whereClause = "isAdmin = ?";
                valueClause = value.toString();
            }
            default -> System.out.println("Unknown search type");
        }
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Users WHERE" + whereClause + ";");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                // return object
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return EMPTY;
    }

    @Override
    public List<User> findAll() throws Exception {
        try {
            connect();
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Users;");
            ResultSet rs = stm.executeQuery();
            if(rs.next()) {
                // return all objects
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close();
        }
        return EMPTY;
    }
}
