package com.helpfox.main.core.manager;

import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.util.HashMap;

public abstract class Fragment extends AnchorPane {

    static final int INITIALIZING = 0;
    static final int CREATED = 1;
    static final int ACTIVITY_CREATED = 2;
    static final int STOPPED = 3;
    static final int STARTED = 4;
    static final int RESUMED = 5;

    @FXML
    private Label label;

    private HashMap arguments;
    String tag;
    String fragmentId;
    String containerId;

    int state = INITIALIZING;

    boolean added;
    boolean detached;
    boolean removing;

    Timeline animatingAway;

    FragmentManagerImpl fragmentManager;

    public Fragment() {
        onCreate();
    }

    void initState(){
        added = false;
        detached = false;
    }

    public final Node findViewByID(String id) {
        return this.lookup("#" + id);
    }

    public void setArguments(HashMap arguments) {
        this.arguments = arguments;
    }

    public HashMap getArguments() {
        return arguments;
    }


    public void onResume() {
        this.state=RESUMED;
    }

    public void onPause(){
    }
    public void onStop() {
        this.state=STOPPED;
    }

    public void onDestroy(){
    }
    public void onDestroyView(){
    }

    public void onDetach(){
    }


    public void onCreate() {
        this.state=CREATED;
    }

    public abstract void onCreateView(FXMLLoader loader);

    public int getState() {
        return state;
    }

    public FragmentManager getFragmentManager() {
        return fragmentManager;
    }

    public final Activity getActivity(){
       return  fragmentManager.activityHost;
    };
}

