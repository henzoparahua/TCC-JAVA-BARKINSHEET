package com.helpfox.main;

import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.SQLite.SqliteUserDAO;
import com.helpfox.main.Model.User.UserDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    private UserDAO buildDAO() {
        //Change the return to implement another
        return new SqliteUserDAO();
    }

    private Office buildModel() {
        return new Office(buildDAO());
    }
    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getViewFactory().showLoginWindow();

    }
    public static void main(String[] args) throws SQLException {
        launch(args);

//      The code below start a local database test, if there is any error, comment start function and/or launch(args)

//        UserDAO userDAO = new SqliteUserDAO();

//        userDAO.setup();

//        Office model = new Office(userDAO);
//
//        model.addNewUser("José", "jose@exemplo.com", "pass", true);
//        model.addNewUser("Rodrigo", "rodrigo@exemplo.com", "123", false);
//        model.addNewUser("John Due", "john@due.com", "456", false);

//        System.out.println(userDAO.findAll());
//
//        userDAO.close();
//        System.exit(0);
    }
}