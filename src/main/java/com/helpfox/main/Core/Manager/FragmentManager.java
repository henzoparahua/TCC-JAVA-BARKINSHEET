package com.helpfox.main.Core.Manager;

import java.util.ArrayList;

public abstract class FragmentManager {
    public abstract FragmentTransaction beginTransaction();
    public abstract Fragment findFragmentByTag(String tag);
}

final class FragmentManagerImpl extends FragmentManager{

    int currentState=Fragment.INITIALIZING;
    protected final Activity activityHost;
    protected ArrayList<Fragment> added;
    protected BackStackRecord backStack;

    public FragmentManagerImpl(Activity activityHost){
        this.activityHost=activityHost;
        added=new ArrayList<>();
    }

    @Override
    public FragmentTransaction beginTransaction() {
        return new BackStackRecord(this);
    }

    @Override
    public Fragment findFragmentByTag(String tag) {
        if(added!=null){
            for(Fragment f:added){
                if(f.tag.equals(tag)){
                    return f;
                }
            }
        }

        return null;
    }

    public void setBackStack(BackStackRecord backStack) {
        this.backStack = backStack;
    }
}
