package com.helpfox.main.Core.Manager;

import java.util.HashMap;

public class Intent {
     protected Class<?> activity;
     protected HashMap extras;

     public Intent(HashMap extras,Class<?> activity){
         this.extras=extras;
         this.activity=activity;
     }

    public HashMap getExtras() {
        return extras;
    }
    public void putExtras(HashMap extras) {
        this.extras = extras;
    };


}
