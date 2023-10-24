package com.helpfox.main.core.framework;;

import com.helpfox.main.core.components.layout.PagerAdapter;
import com.helpfox.main.core.manager.Fragment;
import com.helpfox.main.core.manager.FragmentManager;
import com.helpfox.main.core.manager.FragmentTransaction;
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
