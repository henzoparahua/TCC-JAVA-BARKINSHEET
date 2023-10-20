package com.helpfox.main.Utils;

import com.helpfox.main.Dao.DriverDAO;
import com.helpfox.main.Dao.GatewayDAO;
import com.helpfox.main.Database.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Database.SQLite.SQLiteGatewayDAO;
import com.helpfox.main.Database.SQLite.SQLiteUserDAO;
import com.helpfox.main.Database.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Dao.UserDAO;
import com.helpfox.main.Dao.VehicleDAO;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class DBUtils {
    private static final String CONFIG_FILE = "config.properties";

    public static boolean configFileExists() {
        // Create a File object for the database file in the project's root directory
        File configFile = new File(CONFIG_FILE);

        // Check if the file exists
        return configFile.exists();
    }

    public static boolean createConfigFile() {
        File file = new File(CONFIG_FILE);
        try {
            if (file.createNewFile()) {
                System.out.println("Config created: " + CONFIG_FILE);
                return true;
            } else {
                System.out.println("Config already exists: " + CONFIG_FILE);
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static void setupDatabase() {
        UserDAO userDAO = new SQLiteUserDAO();
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        GatewayDAO gatewayDAO = new SQLiteGatewayDAO();

        try {
            userDAO.setup();
            driverDAO.setup();
            vehicleDAO.setup();
            gatewayDAO.setup();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isSetupCompleted() {
        Properties properties = new Properties();

        try (InputStream input = new FileInputStream(CONFIG_FILE)) {
            properties.load(input);
            String setupCompleted = properties.getProperty("setup.completed");
            return Boolean.parseBoolean(setupCompleted);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void setSetupCompleted(boolean completed) {
        Properties properties = new Properties();

        try (OutputStream output = new FileOutputStream(CONFIG_FILE)) {
            properties.setProperty("setup.completed", String.valueOf(completed));
            properties.store(output, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean allDatabaseExists(List<String> databaseFileNames) {
        for (String databaseFileName : databaseFileNames) {
            // Create a Path object for the current database file in the project's root directory
            Path databaseFilePath = Paths.get(databaseFileName);

            // Check if the file exists using Files.exists() method
            if (!Files.exists(databaseFilePath)) {
                return false; // Return true if any of the database files exists
            }
        }

        return true; // Return false if none of the database files exists
    }
}
