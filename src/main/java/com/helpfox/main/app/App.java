package com.helpfox.main.app;

import com.helpfox.main.core.components.component.MaterialButton;
import com.helpfox.main.core.manager.ActivityFactory;
import com.helpfox.main.server.dao.UserDAO;
import com.helpfox.main.server.database.sqlite.SQLiteUserDAO;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import static com.helpfox.main.core.Css.*;
import static com.helpfox.main.server.util.DBUtils.*;

public class App extends Application {

    @Override
    public void init() {
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
        StackPane root = new StackPane(); // Create root pane
        stage.setScene(new Scene(root)); // Set the scene in the stage

        stage.setTitle("Barkin"); // Set the title of the stage
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logo.png"))); // Set the icon of the stage

        // this object represent the stack  of activities  in your application
        ActivityFactory factory = new ActivityFactory(stage);

        // set the material design style in your application
        stage.getScene().getStylesheets().add(MaterialButton.class.getResource(CONTROLS.toString()).toExternalForm());
        stage.getScene().getStylesheets().add(getClass().getResource(STYLES.toString()).toExternalForm());

        factory.startActivity(MainActivity.class); // start the activity
        stage.show();

        UserDAO userDAO = new SQLiteUserDAO();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}