package com.helpfox.main.Http.Resource;

import java.time.LocalDateTime;

public class GatewayResource {
    public int id;
    public LocalDateTime input;
    public LocalDateTime output;
    public boolean permanence;
    public int driver_id;

    public GatewayResource(int id, LocalDateTime input, LocalDateTime output, boolean permanence, int driver_id) {
        this.id = id;
        this.input = input;
        this.output = output;
        this.permanence = permanence;
        this.driver_id = driver_id;
    }

    public int getId() {
        return id;
    }

    public LocalDateTime getInput() {
        return input;
    }

    public void setInput(LocalDateTime input) {
        this.input = input;
    }

    public LocalDateTime getOutput() {
        return output;
    }

    public void setOutput(LocalDateTime output) {
        this.output = output;
    }

    public boolean isPermanence() {
        return permanence;
    }

    public void setPermanence(boolean permanence) {
        this.permanence = permanence;
    }

    public int getDriver_id() {
        return driver_id;
    }

    public void setDriver_id(int driver_id) {
        this.driver_id = driver_id;
    }
}
