package com.helpfox.main.Model.Gateway;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

public class Gateway {
    private long uid;
    private long uidVehicle;
    private LocalDate entry_date;
    private Time entry_time;
    private Time exit_time;
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




    public Time getEntry_time() {
        return entry_time;
    }

    public void setEntry_time(Time entry_time) {
        this.entry_time = entry_time;
    }

    public Time getExit_time() {
        return exit_time;
    }

    public void setExit_time(Time exit_time) {
        this.exit_time = exit_time;
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

    public LocalDate getEntry_date() {
        return entry_date;
    }

    public void setEntry_date(LocalDate entry_date) {
        this.entry_date = entry_date;
    }
}
