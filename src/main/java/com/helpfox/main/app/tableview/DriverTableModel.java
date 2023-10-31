package com.helpfox.main.app.tableview;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class DriverTableModel {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty name;
    private final SimpleStringProperty rg;
    private final SimpleStringProperty phone;
    private final SimpleStringProperty addVehicle;
    private final SimpleStringProperty edit;
    private final SimpleStringProperty delete;

    public DriverTableModel( String name, String rg, String phone, String addVehicle, String edit, String delete) {
        this.selected = new SimpleBooleanProperty(false);
        this.name = new SimpleStringProperty(name);
        this.rg = new SimpleStringProperty(rg);
        this.phone = new SimpleStringProperty(phone);
        this.addVehicle = new SimpleStringProperty(addVehicle);
        this.edit = new SimpleStringProperty(edit);
        this.delete = new SimpleStringProperty(delete);
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getRg() {
        return rg.get();
    }

    public SimpleStringProperty rgProperty() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg.set(rg);
    }

    public String getPhone() {
        return phone.get();
    }

    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddVehicle() {
        return addVehicle.get();
    }

    public SimpleStringProperty addVehicleProperty() {
        return addVehicle;
    }

    public void setAddVehicle(String addVehicle) {
        this.addVehicle.set(addVehicle);
    }

    public String getEdit() {
        return edit.get();
    }

    public SimpleStringProperty editProperty() {
        return edit;
    }

    public void setEdit(String edit) {
        this.edit.set(edit);
    }

    public String getDelete() {
        return delete.get();
    }

    public SimpleStringProperty deleteProperty() {
        return delete;
    }

    public void setDelete(String delete) {
        this.delete.set(delete);
    }
}
