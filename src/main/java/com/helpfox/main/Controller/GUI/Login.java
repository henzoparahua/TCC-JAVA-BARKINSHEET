package com.helpfox.main.Controller.GUI;

import com.helpfox.main.App;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import static com.helpfox.main.App.setRoot;

public class Login {

    @FXML
    void switchToMenu(ActionEvent event) throws IOException {
        App.setRoot("isADMIN/admMenuGUI");
    }

}
