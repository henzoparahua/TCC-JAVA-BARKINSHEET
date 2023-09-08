package com.helpfox.main.Model;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.util.List;

public class CheckingAccount {
    private UserDAO userDAO;

    public CheckingAccount(UserDAO userDAO) { this.userDAO = userDAO; }

    public Boolean verifyUser(String email) {
        List<User> user = userDAO.findByProp(UserSearchType.EMAIL, email);

        if(!user.isEmpty()) {
            return true;
        } else { return false; }
    }
}
