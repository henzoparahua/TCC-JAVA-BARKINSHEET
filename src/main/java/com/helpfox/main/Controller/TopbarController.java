package com.helpfox.main.Controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class TopbarController {

    private String parentFXML;
    public Button btHelp;
    public Button btConfig;
    public Button btMinimize;
    public Button btClose;

    public void setParentFXML(String parentFXML) {
        this.parentFXML = parentFXML;
    }

    public void Close(ActionEvent actionEvent) {
        Stage stage = (Stage) btClose.getScene().getWindow();
        Model.getInstance().getViewFactory().closeStage(stage);
    }

    public void Minimize(ActionEvent actionEvent) {
        Stage stage = (Stage) btMinimize.getScene().getWindow();
        Model.getInstance().getViewFactory().minimizeStage(stage);
    }

    public void Config(ActionEvent actionEvent) {
        /*Parent parentStage = (Parent) btMinimize.getScene().getRoot();
        getNodeById(parentStage, "rootLogin");*/
    }

    public void Help(ActionEvent actionEvent) {
    }
}
