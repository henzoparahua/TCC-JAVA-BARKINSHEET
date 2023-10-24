package com.helpfox.main.core.components.component;

import javafx.scene.control.TableColumn;

public class FlexColumn<S, T> extends TableColumn<S, T> {

    private double weight;

    public FlexColumn() {
        this.getStyleClass().add("flex-column");
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {

        this.weight = weight;
    }
}
