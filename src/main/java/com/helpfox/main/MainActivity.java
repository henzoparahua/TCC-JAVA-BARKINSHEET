package com.helpfox.main;

import com.helpfox.main.core.manager.Activity;

public class MainActivity extends Activity {
    @Override
    public void onCreate() {
        super.onCreate();
        setContentView(getClass().getResource("main_activity.fxml"));
    }

    public void login(){
        startActivity(AppActivity.class);
    }
}
