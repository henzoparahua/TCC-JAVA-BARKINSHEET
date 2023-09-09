package com.helpfox.main.Model.Office;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.util.List;

public class Office {
    private UserDAO userDAO;
    List<Object> models;

    public Office(List<Object> models) {
        this.models = models;
    }

    public void addNewUser(String name, String email, String password, Boolean isAdmin) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setAdmin(isAdmin);

        userDAO.insertUser(user);
    }

    public boolean createRoleForNewUser(SetAdminType setAdmin) {
        switch (setAdmin) {
            case TRUE -> {
                return true;
            }
            case FALSE -> {
                return false;
            }
            default -> {
                return false;
            }
        }
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