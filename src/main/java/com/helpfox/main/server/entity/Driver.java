package com.helpfox.main.server.entity;

import java.util.Objects;

public class Driver {
    private long id;
    private String name;
    private String rg;
    private String phone;
    private boolean permanence;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public boolean isPermanence() {
        return permanence;
    }

    public void setPermanence(boolean permanence) {
        this.permanence = permanence;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rg='" + rg + '\'' +
                ", phone='" + phone + '\'' +
                ", permanence=" + permanence +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Driver driver)) return false;
        return isPermanence() == driver.isPermanence() && Objects.equals(getName(), driver.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), isPermanence());
    }
}
