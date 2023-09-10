package com.helpfox.main;

import com.helpfox.main.Model.Driver.Driver;
import com.helpfox.main.Model.Driver.DriverDAO;
import com.helpfox.main.Model.Model;
import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.SQLite.SQLiteDriverDAO;
import com.helpfox.main.Model.SQLite.SQLiteVehicleDAO;
import com.helpfox.main.Model.SQLite.SqliteUserDAO;
import com.helpfox.main.Model.SecurityGuard.SecurityGuard;
import com.helpfox.main.Model.User.UserDAO;
import com.helpfox.main.Model.Vehicle.Vehicle;
import com.helpfox.main.Model.Vehicle.VehicleDAO;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;

public class App extends Application {
    private UserDAO buildDAO() {
        //Change the return to implement another
        return new SqliteUserDAO();
    }
    private DriverDAO driverDAO(){
        return new SQLiteDriverDAO();
    }
    private VehicleDAO vehicleDAO(){
        return new SQLiteVehicleDAO();
    }
    private Office buildModel() {
        return new Office(buildDAO());
    }
    private SecurityGuard buildDriver(){
        return new SecurityGuard(driverDAO(), vehicleDAO());
    }

    @Override
    public void start(Stage stage) throws IOException {
        Model.getInstance().getViewFactory().showLoginWindow();
    }

    public static void main(String[] args) throws SQLException {

//      The code below start a local database test, if there is any error, comment start function and/or launch(args)

        UserDAO userDAO = new SqliteUserDAO();
        DriverDAO driverDAO = new SQLiteDriverDAO();
        VehicleDAO vehicleDAO = new SQLiteVehicleDAO();
        Office model = new Office(userDAO);
        SecurityGuard securityGuard = new SecurityGuard(driverDAO, vehicleDAO);

        model.addNewUser("José", "jose@exemplo.com", "pass", true);
        model.addNewUser("Rodrigo", "rodrigo@exemplo.com", "123", false);
        model.addNewUser("John Due", "john@due.com", "456", false);
        securityGuard.addNewDriver("nome", "7676766","56565656");
        System.out.println(userDAO.findAll());
        System.out.println(driverDAO.findAll());
//
        userDAO.close();

        launch(args);

    }
}