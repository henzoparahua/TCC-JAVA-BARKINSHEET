package com.helpfox.main.View;

import com.helpfox.main.App;
import com.helpfox.main.Controller.User.UserController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
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
                dashboardView = (AnchorPane) loadFXML("isUser/enterDashboard");
            }catch (Exception e){
                e.printStackTrace();
            }
        }return dashboardView;
    }
    public void showLoginWindow() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/helpfox/main/FXMLs/LoginGUI.fxml"));
        createStage(loader);
    }
    public void setRoot(Scene scene, String fxml) throws  IOException{
        scene.setRoot(loadFXML(fxml));
    }
    public FXMLLoader listDriverItem () throws  IOException{
        return new FXMLLoader(getClass().getResource("/com/helpfox/main/FXMLs/isUser/driverCell.fxml"));
    }

    public void showAddDriverPopUp(AnchorPane parent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/com/helpfox/main/FXMLs/isUser/driverFormsPopUp.fxml"));
        popUp(loader);
    }

    public void showAddVehiclePopUp(VBox parent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/com/helpfox/main/FXMLs/isUser/vehicleFormsPopUp.fxml"));
        popUp(loader);
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

    private void createStage(FXMLLoader loader) throws IOException {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("BarkinSheet");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    private void popUp(FXMLLoader loader) throws IOException {
        Scene scene = null;
        try {
            scene = new Scene(loader.load());
        } catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.toFront();
        stage.setTitle("BarkinSheet");
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.showAndWait();
    }

    public void closeStage(Stage stage){
        stage.close();
    }
    public void minimizeStage (Stage stage) {stage.setIconified(true);}

    public void createTopbar (AnchorPane parent) throws IOException {
        HBox topbar = (HBox) loadFXML("Topbar");
        AnchorPane.setRightAnchor(topbar, 0.0);
        AnchorPane.setTopAnchor(topbar, 0.0);
        parent.getChildren().add(topbar);
    }

    public void createTopbarforLogin (AnchorPane parent) throws IOException {
        HBox topbar = (HBox) loadFXML("Topbar");
        AnchorPane.setRightAnchor(topbar, 0.0);
        AnchorPane.setTopAnchor(topbar, 0.0);
        parent.getChildren().add(topbar);
        topbar.getChildren().remove(1);
        topbar.getChildren().remove(0);
    }
}
