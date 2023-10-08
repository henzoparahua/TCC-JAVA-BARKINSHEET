package com.helpfox.main.Resource;

import java.time.LocalDateTime;

public class VehicleResource {
    private int id;
    private String brand;
    private String color;
    private String plate;
    private String observation;
    private int driver_id;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public VehicleResource(int id, String brand, String color, String plate, String observation, int driver_id, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.brand = brand;
        this.color = color;
        this.plate = plate;
        this.observation = observation;
        this.driver_id = driver_id;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

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

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
}
