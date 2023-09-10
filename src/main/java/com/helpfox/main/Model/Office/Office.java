package com.helpfox.main.Model.Office;

import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;
import com.helpfox.main.Model.Vehicle.VehicleDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Office {
    private final UserDAO userDAO;
    private DriverDAO driverDAO;
    private VehicleDAO vehicleDAO;
    List<Object> daoInstances = new ArrayList<>();

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
    }
}