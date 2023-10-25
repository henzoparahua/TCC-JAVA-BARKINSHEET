package com.helpfox.main.server.http.Resource;

import java.time.LocalDateTime;
import java.util.List;

public class DriverResource {
    public int id;
    public String name;
    public String rg;
    public String phone;
    public List<VehicleResource> vehicles;
    public LocalDateTime created_at;
    public LocalDateTime updated_at;

    public DriverResource(int id, String name, String rg, String phone, List<VehicleResource> vehicles, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.rg = rg;
        this.phone = phone;
        this.vehicles = vehicles;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<VehicleResource> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleResource> vehicles) {
        this.vehicles = vehicles;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }
}