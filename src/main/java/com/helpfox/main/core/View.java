package com.helpfox.main.core;

public enum View {
    MAINACACTIVITY("main_activity"),
    APPACTIVITY("app_activity"),
    DRIVERFRAGMENT("fragments/driver_fragment"),
    DETAILSFRAGMENT("details_fragment"),
    NAVITEM("nav_item"),
    DRIVERITEM("driver_item");

    public final String location;

    View(String location) {
        this.location = "/view/" + location + ".fxml";
    }

    @Override
    public String toString() {
        return this.location;
    }
}
