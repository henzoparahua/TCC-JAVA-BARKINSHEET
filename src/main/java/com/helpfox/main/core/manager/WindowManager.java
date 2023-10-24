package com.helpfox.main.core.manager;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;

public abstract class WindowManager extends ActivityBackStack {
    protected final Window window;
    protected final ObservableList<Node> windowPane;

    public WindowManager(Stage window){
        this.window=window;
         windowPane=((Pane)window.getScene().getRoot()).getChildren();
    }
   @Override
    public void setContentView(Node content){
        windowPane.add(content);
    }

    @Override
    public Node getDecorView() {
       return windowPane.get(windowPane.size()-1);
    }
}
