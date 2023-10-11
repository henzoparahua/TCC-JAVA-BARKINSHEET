package com.helpfox.main.Controller.User;

public class VehicleItem {

    private int vehiclepos;
    private String name;
    private String Plate_one;
    private String Plate_two;
    private String Plate_three;

    public VehicleItem(Integer i, String name, String plate_one, String plate_two, String plate_three){
        this.vehiclepos = i;
        this.name = name;
        this.Plate_one = plate_one;
        this.Plate_two = plate_two;
        this.Plate_three = plate_three;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlate_one() {
        return Plate_one;
    }

    public void setPlate_one(String plate_one) {
        Plate_one = plate_one;
    }

    public String getPlate_two() {
        return Plate_two;
    }

    public void setPlate_two(String plate_two) {
        Plate_two = plate_two;
    }

    public String getPlate_three() {
        return Plate_three;
    }

    public void setPlate_three(String plate_three) {
        Plate_three = plate_three;
    }

    public int getVehiclepos() {
        return vehiclepos;
    }
}
