package com.helpfox.main.core;

public enum View {
    LOGIN("Login"),
    SIGNIN("Signin"),
    DRIVER("Driver"),
    DRIVERFORM("DriverForm"),
    GATEWAY("Gateway"),
    GATEWAYFORM("GatewayForm");


    public final String location;

    View(String location) {
        this.location = "../Resource/View/" + location + ".fxml";
    }
}
