package com.helpfox.main.core.framework;

import com.helpfox.main.core.components.component.IconButton;
import com.helpfox.main.core.manager.ActionBar;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class Toolbar extends VBox implements ActionBar {
    private final HBox topPlace = new HBox();
    private final Label title = new Label();
    private final IconButton home = new IconButton();
    private boolean homeAsUp = false;

    public Toolbar(){
        getStyleClass().add("toolbar");
        setAlignment(Pos.CENTER_LEFT);
        topPlace.setMinHeight(64);
        topPlace.setAlignment(Pos.CENTER_LEFT);
        topPlace.getChildren().add(title);
        topPlace.getStyleClass().add("top-place");
        title.getStyleClass().add("title");

        home.setIcon(getClass().getResourceAsStream("/framework/menu.svg"));
        home.setIconBackground(getClass().getResourceAsStream("/framework/arrow_back.svg"));

        getChildren().add(topPlace);
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }


    @Override
    public void setDisplayHomeAsUpEnabled(boolean showHomeAsUp) {
        homeAsUp = true;
        home.setIcon(getClass().getResourceAsStream("/framework/arrow_back.svg"));
        home.setVisible(false);
        home.setManaged(false);
        if(!topPlace.getChildren().contains(home)){
            topPlace.getChildren().add(0, home);
        }
    }

    @Override
    public void setDisplayShowHomeEnabled(boolean showHome) {
        if(!topPlace.getChildren().contains(home)){
            topPlace.getChildren().add(0,home);
        }

    }

    @Override
    public void showHomeAsUp() {
        home.setVisible(true);
        home.setManaged(true);
    }

    @Override
    public boolean isDisplayHomeAsUpEnabled() {
        return homeAsUp;
    }

    @Override
    public void setOnHomeButtonAction(EventHandler<ActionEvent> handler){
        home.setOnAction(handler);
    }


}
