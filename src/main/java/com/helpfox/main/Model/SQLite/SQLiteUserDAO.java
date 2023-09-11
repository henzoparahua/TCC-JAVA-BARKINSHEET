package com.helpfox.main.Model.SQLite;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteUserDAO implements UserDAO {
    private Connection connection;
    private final List<User> EMPTY = new ArrayList<>();
    private final ArrayList<User> users = new ArrayList<>();

    @Override
    public void setup() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:users.db");
            PreparedStatement stm = connection.prepareStatement("CREATE TABLE Users (" +
                    "uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name VARCHAR(60) NOT NULL," +
                    "email VARCHAR(60) NOT NULL," +
                    "password VARCHAR(30) NOT NULL," +
                    "isAdmin BOOLEAN)");
            stm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void connect() throws SQLException {
        try {
            if(connection == null) {
                connection = DriverManager.getConnection("jdbc:sqlite:users.db");
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
    public long insertUser(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("INSERT INTO Users VALUES (?,?,?,?,?)");
            stm.setString(2, user.getName());
            stm.setString(3, user.getEmail());
            stm.setString(4, user.getPassword());
            stm.setBoolean(5, user.getIsAdmin());
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
    public boolean updateUser(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("UPDATE Users SET name=?, email=?, password=?, isAdmin=? WHERE uid=?");
            stm.setString(1, user.getName());
            stm.setString(2, user.getEmail());
            stm.setString(3, user.getPassword());
            stm.setBoolean(4, user.getIsAdmin());
            stm.setLong(5, user.getUid());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        try {
            PreparedStatement stm = connection.prepareStatement("DELETE FROM Users WHERE uid=?");
            stm.setLong(1, user.getUid());
            stm.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<User> findByProp(UserSearchType searchType, Object value) {
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
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Users WHERE " + whereClause);
            stm.setString(1, valueClause);
            ResultSet rs = stm.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setUid(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAdmin(rs.getBoolean(5));

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }

    @Override
    public List<User> findAll() {
        try {
            PreparedStatement stm = connection.prepareStatement("SELECT * FROM Users ORDER BY uid ASC");
            ResultSet rs = stm.executeQuery();

            while(rs.next()) {
                User user = new User();
                user.setUid(rs.getLong(1));
                user.setName(rs.getString(2));
                user.setEmail(rs.getString(3));
                user.setPassword(rs.getString(4));
                user.setAdmin(rs.getBoolean(5));

                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return EMPTY;
    }
}
