package com.helpfox.main.Controller.User;

public class VehicleItem {

    private static int vehicleId = 1;
    private int vehicleuid;
    private String name;
    private String Plate_one;

    public VehicleItem(String name, String plate_one){
        vehicleuid = vehicleId++;
        this.name = name;
        this.Plate_one = plate_one;
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

    public String getPlate_one() {
        return Plate_one;
    }

    public void setPlate_one(String plate_one) {
        Plate_one = plate_one;
    }
}
