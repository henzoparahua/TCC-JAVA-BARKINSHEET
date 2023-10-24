package com.helpfox.main.Core.Components.Component;

import com.helpfox.main.Core.Components.Layout.RecyclerView;
import javafx.scene.control.skin.ListCellSkin;
import javafx.scene.control.Skin;

public class RippleViewRow extends RecyclerView.ViewRow {

    public RippleViewRow(RecyclerView.Adapter adapter) {
        super(adapter);
    }

    @Override
    protected Skin<?> createDefaultSkin() {
        ListCellSkin listCellSkin=new ListCellSkin(this);
        RippleSkinFactory.getRippleEffect(listCellSkin, this);
        return listCellSkin;
    }
}
