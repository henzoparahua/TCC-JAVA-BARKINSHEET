package com.helpfox.main.Model;

import com.helpfox.main.Entity.User;
import com.helpfox.main.Dao.UserDAO;
import com.helpfox.main.Type.SetAdminType;
import com.helpfox.main.Type.UserSearchType;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Office {
    private final UserDAO userDAO;

    public Office(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void addNewUser(String name, String email, String password, Boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(isAdmin);

        try {
            userDAO.connect();
            userDAO.insertUser(user);
            userDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean createRoleForNewUser(SetAdminType setAdmin) {
        boolean f = false;
        switch (setAdmin) {
            case TRUE -> {
                return true;
            }
            case FALSE -> {
                return false;
            }
        }
        return f;
    }

    public void manageRole(long uid, SetAdminType setAdmin) {
        try {
            userDAO.connect();
            List<User> users = userDAO.findByProp(UserSearchType.UID, uid);

            switch (setAdmin) {
                case TRUE -> {
                    if (!users.isEmpty()) {
                        users.get(0).setAdmin(true);
                    }
                }
                case FALSE -> {
                    if (!users.isEmpty()) {
                        users.get(0).setAdmin(false);
                    }
                }
            }
            userDAO.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isValidUser(String email, String password) {
        List<User> userList = new ArrayList<>();

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);

        userList.add(user);
        // Check if the username exists in the user database
        if (userExists(userList)) {
            // Retrieve the stored password for the username
            String storedPassword = findByEmail(email).get(0).getPassword();

            // Compare the entered password with the stored password
            return storedPassword.equals(password);
        }

        return false; // Username doesn't exist in the database
    }

    public List<User> findByEmail(String email) {
        List<User> user = new ArrayList<>();
        try {
            userDAO.connect();
            user = userDAO.findByProp(UserSearchType.EMAIL, email);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    public static Boolean userExists(List<User> userList) {
        return !userList.isEmpty();
    }

}