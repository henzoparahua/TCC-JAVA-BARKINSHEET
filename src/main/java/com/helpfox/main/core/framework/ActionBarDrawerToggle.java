package com.helpfox.main.core.framework;


import com.helpfox.main.core.components.layout.DrawerLayout;
import com.helpfox.main.core.manager.Activity;
import javafx.scene.Node;

public class ActionBarDrawerToggle implements DrawerLayout.DrawerListener{

    public ActionBarDrawerToggle(Activity activity, DrawerLayout drawer, Toolbar toolbar){
           toolbar.setOnHomeButtonAction(evt->{
               if(drawer.isDrawerOpened()){
                   drawer.closeDrawer();
               }else{
                   drawer.openDrawer();
               }

           });


    }
    @Override
    public void onDrawerClosed(Node node) {

    }

    @Override
    public void onDrawerOpened(Node node) {

    }
}
