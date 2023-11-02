package com.helpfox.main.app.tableview;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class GatewayTableModel {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty name;
    private final SimpleStringProperty car;
    private final SimpleStringProperty plate;
    private final SimpleStringProperty date;
    private final SimpleStringProperty hour;
    private final SimpleStringProperty type;

    public GatewayTableModel(String name, String car, String plate, String date, String hour, String type) {
        this.selected = new SimpleBooleanProperty(false);
        this.name = new SimpleStringProperty(name);
        this.car = new SimpleStringProperty(car);
        this.plate = new SimpleStringProperty(plate);
        this.date = new SimpleStringProperty(date);
        this.hour = new SimpleStringProperty(hour);
        this.type = new SimpleStringProperty(type);
    }
}
