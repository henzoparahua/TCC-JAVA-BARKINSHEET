package com.helpfox.main.Model.Gateway;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Gateway {
    private long uid;
    private long uidVehicle;
    private String entry_date;
    private String entry_time;
    private String exit_time;
    private String nameDriver;
    private String plateDriver;

    public long getUid() {
        return uid;
    }

    public void setUid(long uid) {
        this.uid = uid;
    }

    public long getUidVehicle() {
        return uidVehicle;
    }

    public void setUidVehicle(long uidVehicle) {
        this.uidVehicle = uidVehicle;
    }

    public String getNameDriver() {
        return nameDriver;
    }

    public void setNameDriver(String nameDriver) {
        this.nameDriver = nameDriver;
    }

    public String getPlateDriver() {
        return plateDriver;
    }

    public void setPlateDriver(String plateDriver) {
        this.plateDriver = plateDriver;
    }

    public String getExit_time() {
        return exit_time;
    }

    public void setExit_time(String exit_time) {
        this.exit_time = exit_time;
    }

    public String getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(String entry_time) {
        this.entry_time = entry_time;
    }

    public String getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(String entry_date) {
        this.entry_date = entry_date;
    }
}
