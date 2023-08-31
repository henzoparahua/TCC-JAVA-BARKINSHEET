package com.helpfox.main.View;

import com.helpfox.main.App;
import com.helpfox.main.Controller.User.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ViewFactory {
    // Client View
    private AnchorPane dashboardView;

    public ViewFactory(){}

    public AnchorPane getDashboardView(){
        if (dashboardView == null){
            try {
                dashboardView = (AnchorPane) loadFXML("isUser/Dashboard");
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        return dashboardView;
    }
    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helpfox/main/FXMLs/LoginGUI.fxml"));
        createStage(loader);
    }

    public void showClientWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helpfox/main/FXMLs/isUser/User.fxml"));
        UserController userController = new UserController();
        loader.setController(userController);
        createStage(loader);
    }
    public static Parent loadFXML(String fxml) throws  IOException{
        FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("FXMLs/"+fxml+".fxml"));
        return fxmlLoader.load();
    }
    private void createStage(FXMLLoader loader){

        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("BarkinSheet");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
}
