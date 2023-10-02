package com.helpfox.main.Model.Driver;

import java.util.Objects;

public class Driver {
    private long uid;
    private String nameDriver;
    private String brand;
    private String color;
    private String plate;
    private String rg;
    private String phone;
    private boolean permanence;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
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

    public boolean isPermanence() {
        return permanence;
    }

    public void setPermanence(boolean permanence) {
        this.permanence = permanence;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "uid=" + uid +
                ", nameDriver='" + nameDriver + '\'' +
                ", rg='" + rg + '\'' +
                ", phone='" + phone + '\'' +
                ", permanence=" + permanence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        return Objects.equals(getNameDriver(), driver.getNameDriver());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNameDriver());
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }
}
