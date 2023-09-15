package com.helpfox.main.Controller.User;

public class VehicleItem {

    private static int vehicleId = 1;
    private int vehicleuid;
    private String name;

    public VehicleItem(String name){
        vehicleuid = vehicleId++;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getVehicleuid() {
        return vehicleuid;
    }
}
