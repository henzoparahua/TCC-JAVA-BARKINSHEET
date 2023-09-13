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

                List<String> databaseFileNames = List.of("users.db", "drivers.db", "vehicles.db");
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
    public static void main(String[] args) throws SQLException { launch(args); } }