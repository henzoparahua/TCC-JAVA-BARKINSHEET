package com.helpfox.main.Model;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.List;

public class SqliteUserDAO implements UserDAO {
    private Connection connection;
    @Override
    public void setup() throws Exception {
        connection = DriverManager.getConnection("jdbc:sqlite:users.db;create=true");
        PreparedStatement stm = connection.prepareStatement("CREATE TABLE Users (" +
                "uid INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(60)," +
                "email VARCHAR(60)," +
                "password VARCHAR(30)," +
                "isAdmin BOOLEAN);");
        stm.executeUpdate();
    }

    @Override
    public void connect() throws Exception {

    }

    @Override
    public void close() throws Exception {

    }

    @Override
    public long insertUser(User user) {
        return 0;
    }

    @Override
    public boolean updateUser(User user) {
        return false;
    }

    @Override
    public boolean deleteUser(User user) {
        return false;
    }

    @Override
    public List<User> findByProp(UserSearchType searchType, Object value) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}
