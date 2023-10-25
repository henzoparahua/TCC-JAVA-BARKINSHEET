package com.helpfox.main.core;

public enum Css {
    STYLES("/css/styles.css"),
    CONTROLS("/css/component/controls.css");

    public final String location;
    Css(String location) {
        this.location = location;
    };

    @Override
    public String toString() {
        return this.location;
    }
}
