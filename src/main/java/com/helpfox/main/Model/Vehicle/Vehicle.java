package com.helpfox.main.Model.Vehicle;

import java.util.Objects;

public class Vehicle {

    private long uid;
    private Long uidDriver;
    private String brand;
    private String color;
    private String plate;
    private String observation;
    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public Long getUidDriver() { return uidDriver; }

    public void setUidDriver(Long uidDriver) { this.uidDriver = uidDriver; }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "uid=" + uid +
                ", uidDriver='"+uidDriver +'\''+
                ", brand='" + brand + '\'' +
                ", color='" + color + '\'' +
                ", plate='" + plate + '\'' +
                ", observation='" + observation + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehicle vehicle)) return false;
        return Objects.equals(getBrand(), vehicle.getBrand()) && Objects.equals(getColor(), vehicle.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBrand(), getColor());
    }

}
