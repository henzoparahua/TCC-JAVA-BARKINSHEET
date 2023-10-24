package com.helpfox.main.core.components.component;

import javafx.scene.control.ButtonBase;
import javafx.scene.control.SkinBase;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.SVGPath;

public  class IconButtonSkin extends SkinBase {
    private final StackPane base = new StackPane();

    public IconButtonSkin(ButtonBase button) {
        super(button);
        base.setMouseTransparent(true);
        getChildren().add(base);
    }

    public void setIcon(SVGPath icon) {
        if(icon!=null) {
            base.getChildren().add(icon);
        }
    }

    public StackPane getBase() {
        return base;
    }
}