package com.helpfox.main.Core.Framework;;

import com.helpfox.main.Core.Components.Layout.PagerAdapter;
import com.helpfox.main.Core.Manager.Fragment;
import com.helpfox.main.Core.Manager.FragmentManager;
import com.helpfox.main.Core.Manager.FragmentTransaction;
import javafx.scene.layout.Pane;

public abstract class FragmentStatePagerAdapter extends PagerAdapter {
    private FragmentManager fragmentManager;
    private int currentItem;
    public FragmentStatePagerAdapter(FragmentManager fragmentManager){
        this.fragmentManager = fragmentManager;
    }
    public abstract Fragment getItem(int position);
    @Override
    public Object instantiateItem(Pane container, int position) {
        if(position<getCount()){
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(container.getId(),getItem(position));
            transaction.commit();
        }
        return null;
    }
}
