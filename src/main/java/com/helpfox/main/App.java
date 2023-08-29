package com.helpfox.main;

import com.helpfox.main.Model.Office.Office;
import com.helpfox.main.Model.SQLite.SqliteUserDAO;
import com.helpfox.main.Model.User.UserDAO;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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

    Image appIcon = new Image(getClass().getResourceAsStream("View/img/barkinsheetlogo-min.png"));
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View/fxml/LogInGUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1152, 680);
        stage.getIcons().add(appIcon);
        stage.setTitle("BarkinSheet");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);

        stage.show();
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