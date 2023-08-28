
package com.helpfox.main;

import com.helpfox.main.Controller.GUI.topBar;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.sql.SQLException;


public class App extends Application {

    public static Scene scene;
    Image appIcon = new Image(getClass().getResourceAsStream("View/img/barkinsheetlogo-min.png"));
    @Override
    public void start(Stage stage) throws IOException {
        Scene scene = new Scene(loadFXML("LoginGUI"),1152, 680);


        stage.getIcons().add(appIcon);
        stage.setTitle("BarkinSheet");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();

    }


    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    public static Parent loadFXML(String fxml) throws  IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("View/fxml/"+fxml+".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) throws SQLException {
        launch(args);

//      The code below start a local database test, if there is any error, comment start function and/or launch(args)

//        UserDAO userDAO = new SqliteUserDAO();

//        userDAO.setup();

//        Office model = new Office(userDAO);

//        model.addNewUser("José", "jose@exemplo.com", "pass", true);
//        model.addNewUser("Rodrigo", "rodrigo@exemplo.com", "123", false);
//        model.addNewUser("John Due", "john@due.com", "456", false);

//        System.out.println(userDAO.findAll());

//        userDAO.close();
//        System.exit(0);
    }
}