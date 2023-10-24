package com.helpfox.main.server.entity;

import java.util.Objects;

public class Vehicle {

    private long id;
    private Long idDriver;
    private String brand;
    private String color;
    private String plate;
    private String observation;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Long getIdDriver() { return idDriver; }

    public void setIdDriver(Long idDriver) { this.idDriver = idDriver; }

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
                "id=" + id +
                ", idDriver='"+idDriver +'\''+
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
        return Objects.equals(idDriver, vehicle.idDriver) && Objects.equals(getBrand(), vehicle.getBrand()) && Objects.equals(getColor(), vehicle.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDriver, getBrand(), getColor());
    }
}
