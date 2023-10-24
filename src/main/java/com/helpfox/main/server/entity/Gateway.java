package com.helpfox.main.server.entity;

import java.util.Objects;

public class Gateway {
    private long id;
    private long idDriver;
    private int date;
    private int time;
    private byte parked;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public long getIdDriver() {
        return idDriver;
    }

    public void setIdDriver(long idDriver) {
        this.idDriver = idDriver;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public byte getParked() {
        return parked;
    }

    public void setParked(byte parked) {
        this.parked = parked;
    }

    @Override
    public String toString() {
        return "Gateway{" +
                "id=" + id +
                ", idDriver=" + idDriver +
                ", date=" + date +
                ", time=" + time +
                ", parked=" + parked +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Gateway gateway)) return false;
        return getIdDriver() == gateway.getIdDriver() && getParked() == gateway.getParked() && Objects.equals(getDate(), gateway.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdDriver(), getDate(), getParked());
    }
}
