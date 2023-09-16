package com.helpfox.main;

import com.helpfox.main.Model.Model;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.helpfox.main.Utils.DBUtils.*;

public class App extends Application {

    @Override
    public void init() throws Exception {
        try {
            if (configFileExists()) {
                System.out.println("Running config setup...");
            } else {
                // Create the configuration file first
                createConfigFile();

                List<String> databaseFileNames = List.of("barkin.db");

                if (!allDatabaseExists(databaseFileNames)) {
                    setSetupCompleted(false);
                }
                if (!isSetupCompleted()) {
                    System.out.println("Running database setup...");

                    setupDatabase();

                    // Update configuration to indicate setup completion
                    setSetupCompleted(true);
                } else {
                    // Setup has already been completed
                    System.out.println("Database setup has already been completed.");
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Initial setup hasn't already been completed.");
        }
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