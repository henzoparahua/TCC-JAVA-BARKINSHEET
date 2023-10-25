package com.helpfox.main.core;

public enum View {
    MAINACACTIVITY("main_activity"),
    APPACTIVITY("app_activity"),
    APPFRAGMENT("app_fragment"),
    DETAILSFRAGMENT("details_fragment");

    public final String location;

    View(String location) {
        this.location = "/View/" + location + ".fxml";
    }

    @Override
    public String toString() {
        return this.location;
    }
}
