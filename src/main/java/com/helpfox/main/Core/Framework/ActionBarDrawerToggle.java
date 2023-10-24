package com.helpfox.main.Core.Framework;


import com.helpfox.main.Core.Components.Layout.DrawerLayout;
import com.helpfox.main.Core.Manager.Activity;
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
