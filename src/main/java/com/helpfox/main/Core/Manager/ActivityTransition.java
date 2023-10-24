package com.helpfox.main.Core.Manager;

public abstract class ActivityTransition implements WindowBehavior{

    public abstract void add(Activity activity);
    public abstract ActivityTransition back();
}
