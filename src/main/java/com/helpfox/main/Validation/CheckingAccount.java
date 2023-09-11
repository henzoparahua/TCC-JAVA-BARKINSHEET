package com.helpfox.main.Validation;

import com.helpfox.main.Model.User.User;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.User.UserSearchType;
import javafx.scene.control.Alert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.ArrayList;
import java.util.List;

public class CheckingAccount {
    private final UserDAO userDAO;
    private static final String NAME_REGEX =
            "^[a-zA-Z\\u00C0-\\u00FF\\u0192\\u201A\\u201E\\u2026\\u2020\\u2021\\u2022\\u2026\\u2030\\u2039\\u203A\\u00C3\\u00C5\\u00C6\\u00C7\\u00C8\\u00C9\\u00D1\\u00D2\\u00D3\\u00D4\\u00D5\\u00D6\\u00D8\\u00D9\\u00DA\\u00DB\\u00DC\\u00E0\\u00E1\\u00E2\\u00E3\\u00E4\\u00E5\\u00E6\\u00E7\\u00E8\\u00E9\\u00EA\\u00EB\\u00EC\\u00ED\\u00EE\\u00EF\\u00F0\\u00F1\\u00F2\\u00F3\\u00F4\\u00F5\\u00F6\\u00F8\\u00F9\\u00FA\\u00FB\\u00FC\\u00FD\\u00FE\\u00FF]+$";
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

    public static boolean isValidName(String name) {
        Pattern pattern = Pattern.compile(NAME_REGEX);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
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

    public static void addAlert(SetMsgError setMsgError) {
            switch (setMsgError) {
                case EMPTYNAME -> {
                    alert("Por favor, preencha o nome.");
                }
                case EMPTYEMAIL -> {
                    alert("Por favor, preencha o email.");
                }
                case EMPTYPASSWORD -> {
                    alert("Por favor, preencha a senha.");
                }
                case INVALIDNAME -> {
                    alert("Por favor, o nome precisa ser de letras apenas.");
                }
                case INVALIDEMAIL -> {
                    alert("Por favor, coloque um email válido.");
                }
                case INVALIDPASSWORD -> {
                    alert("Por favor, coloque uma senha com números, letras maiúsculas e minúsculas.");
                }
                case LOGINERROR -> {
                    alert("Ops! Aconteceu algo inesperado.");
                }
            }
    }

    static Alert alert(String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(message);
        alert.showAndWait();
        return alert;
    }
}
