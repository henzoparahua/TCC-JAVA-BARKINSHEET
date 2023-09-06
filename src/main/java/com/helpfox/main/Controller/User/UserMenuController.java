package com.helpfox.main.Controller.User;

import com.helpfox.main.Model.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserMenuController implements Initializable {
    public AnchorPane mainContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            Model.getInstance().getViewFactory().createTopbar(mainContainer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
