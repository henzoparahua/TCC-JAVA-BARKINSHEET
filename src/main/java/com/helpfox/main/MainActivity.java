package com.helpfox.main;

import com.helpfox.main.core.View;
import com.helpfox.main.core.manager.Activity;

import static com.helpfox.main.core.View.MAINACACTIVITY;

public class MainActivity extends Activity {
    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(getClass().getResource(MAINACACTIVITY.toString()));
    }

    public void login(){
        startActivity(AppActivity.class);
    }

    public void signin(){
        startActivity(AppActivity.class);
    }
}
