package com.helpfox.main;

import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SQLite.SqliteUserDAO;
import com.helpfox.main.Model.User.UserDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.helpfox.main.Utils.DAOFinder.buildDAO;

public class App extends Application {
    public static List<String> daoNamesToBuild = List.of("SQLiteUserDAO", "SQLiteDriverDAO", "SQLiteVehicleDAO");

    static List<Object> daoInstances = buildDAO(daoNamesToBuild);

    private Office buildModel() {
        return new Office(buildDAO(daoNamesToBuild));
    }
    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getViewFactory().showLoginWindow();
    }
    public static void main(String[] args) throws SQLException {
        launch(args);

        for (Object dao : daoInstances) {
            System.out.println("This DAO instances: " + dao.getClass().getName());
        }


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