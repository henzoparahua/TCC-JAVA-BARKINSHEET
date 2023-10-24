package com.helpfox.main.Core.Components.Layout;

import javafx.scene.layout.Pane;

public abstract class PagerAdapter {

    public abstract String getPageTitle(int position);
    public abstract int getCount();
    public abstract Object instantiateItem(Pane container,int position);

}
