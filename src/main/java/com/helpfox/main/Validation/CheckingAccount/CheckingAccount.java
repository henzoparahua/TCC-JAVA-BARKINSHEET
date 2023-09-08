package com.helpfox.main.Validation.CheckingAccount;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;


public class CheckingAccount {
    private final UserDAO userDAO;
    private static final String EMAIL_REGEX =
            "^(?=\\S)([a-zA-Z0-9+._%-]+)(@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,})$";
    private static final String PASSWORD_REGEX =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$";

    public static List<User> userList = new ArrayList<>();


    public CheckingAccount(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> findByEmail(String email) {
        return userDAO.findByProp(UserSearchType.EMAIL, email);
    }

    public static Boolean userExists(List<User> userList) {
        return !userList.isEmpty();
    }

    public static boolean isValidEmail(String email) {
        Pattern pattern = Pattern.compile(EMAIL_REGEX);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public static boolean isValidPassword(String password) {
        Pattern pattern = Pattern.compile(PASSWORD_REGEX);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public boolean isValidUser(String email, String password) {
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
}
